package fr.elias.oreoEssentials.inventory.internal.sellgui.provider;

import fr.elias.oreoEssentials.OreoEssentials;
import fr.elias.oreoEssentials.inventory.internal.sellgui.manager.SellGuiManager;
import fr.elias.oreoEssentials.shared.Lang;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SellMenuProvider implements InventoryProvider {

    // PDC keys used to track injected price lore so we can strip it cleanly on return
    private static final NamespacedKey SELL_LORE_COUNT  = new NamespacedKey("oreo", "sell_lore_count");
    private static final NamespacedKey SELL_LORE_AMOUNT = new NamespacedKey("oreo", "sell_lore_amount");

    private final OreoEssentials plugin;
    private final SellGuiManager manager;

    private static boolean isSellSlot(int row, int col) {
        return row >= 1 && row <= 4 && col >= 1 && col <= 7;
    }

    public SellMenuProvider(OreoEssentials plugin, SellGuiManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta gm = glass.getItemMeta();
        if (gm != null) {
            gm.setDisplayName(" ");
            gm.addItemFlags(ItemFlag.values());
            glass.setItemMeta(gm);
        }
        contents.fill(ClickableItem.empty(glass));


        for (int r = 1; r <= 4; r++) {
            for (int c = 1; c <= 7; c++) {
                SlotPos pos = SlotPos.of(r, c);
                contents.set(pos, ClickableItem.empty(null)); // let vanilla stack display
                contents.setEditable(pos, true);
            }
        }

        contents.set(5, 2, ClickableItem.of(
                manager.config().buildButton(
                        "sell",
                        Material.LIME_CONCRETE,
                        "<green><bold>SELL</bold></green>",
                        List.of("<gray>Sell all items placed in the sell area.</gray>")
                ),
                e -> {
                    double total = computeTotal(player);
                    if (total <= 0) {
                        player.sendMessage(manager.config().msgNothingSellable());
                        return;
                    }
                    openConfirm(player, total);
                }
        ));

        contents.set(5, 4, ClickableItem.of(
                manager.config().buildButton(
                        "close",
                        Material.BARRIER,
                        "<red><bold>CLOSE</bold></red>",
                        List.of("<gray>Close the menu (items are returned).</gray>")
                ),
                e -> player.closeInventory()
        ));

        contents.set(5, 6, ClickableItem.of(
                manager.config().buildButton(
                        "clear",
                        Material.RED_CONCRETE,
                        "<yellow><bold>CLEAR</bold></yellow>",
                        List.of("<gray>Return all items to your inventory.</gray>")
                ),
                e -> {
                    returnAllSellItems(player);
                    player.sendMessage(manager.config().msgReturnedAll());
                }
        ));

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        org.bukkit.inventory.Inventory topInv = player.getOpenInventory().getTopInventory();
        boolean changed = false;

        // Inject price lore on items sitting in sell slots
        for (int slot = 0; slot < topInv.getSize(); slot++) {
            int row = slot / 9, col = slot % 9;
            if (!isSellSlot(row, col)) continue;
            ItemStack item = topInv.getItem(slot);
            if (item == null || item.getType() == Material.AIR) continue;
            double price = manager.config().getUnitPrice(item);
            if (injectPriceLore(item, price)) {
                topInv.setItem(slot, item);
                changed = true;
            }
        }

        // Strip injected lore from any items the player moved back to their own inventory
        // (covers clicks, shift-clicks, drags — anything that bypasses returnSellItemsStatic)
        for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
            ItemStack item = player.getInventory().getItem(slot);
            if (item == null || item.getType() == Material.AIR) continue;
            ItemMeta meta = item.getItemMeta();
            if (meta == null || !meta.getPersistentDataContainer().has(SELL_LORE_COUNT, PersistentDataType.INTEGER)) continue;
            stripPriceLore(item);
            player.getInventory().setItem(slot, item);
            changed = true;
        }

        if (changed) player.updateInventory();
    }

    /**
     * Appends sell-price lore to the item. Returns true if the item was actually modified.
     * Skips re-injection when price and amount are unchanged (avoids spamming packets).
     */
    private boolean injectPriceLore(ItemStack item, double unitPrice) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        // Already injected — skip if amount hasn't changed (total would be the same)
        if (pdc.has(SELL_LORE_COUNT, PersistentDataType.INTEGER)) {
            Integer storedAmount = pdc.get(SELL_LORE_AMOUNT, PersistentDataType.INTEGER);
            if (storedAmount != null && storedAmount == item.getAmount()) return false;

            // Amount changed: restore original lore before re-injecting
            int origCount = pdc.getOrDefault(SELL_LORE_COUNT, PersistentDataType.INTEGER, 0);
            List<String> cur = meta.getLore();
            if (origCount == 0) {
                meta.setLore(null);
            } else if (cur != null && cur.size() >= origCount) {
                meta.setLore(new ArrayList<>(cur.subList(0, origCount)));
            }
        } else {
            // First injection: record original lore line count
            List<String> orig = meta.getLore();
            pdc.set(SELL_LORE_COUNT, PersistentDataType.INTEGER, orig != null ? orig.size() : 0);
        }

        pdc.set(SELL_LORE_AMOUNT, PersistentDataType.INTEGER, item.getAmount());

        List<String> lore = meta.getLore() != null ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
        if (!lore.isEmpty()) lore.add("");
        if (unitPrice >= 0) {
            String perItem = formatPrice(unitPrice);
            lore.add(ChatColor.YELLOW + "Sell price: " + ChatColor.GREEN + perItem + " each");
            if (item.getAmount() > 1) {
                lore.add(ChatColor.YELLOW + "Total: " + ChatColor.GREEN + formatPrice(unitPrice * item.getAmount()));
            }
        } else {
            lore.add(ChatColor.RED + "Not sellable");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return true;
    }

    private String formatPrice(double amount) {
        if (plugin.getVaultEconomy() != null) return plugin.getVaultEconomy().format(amount);
        return String.format("$%.2f", amount);
    }

    /**
     * Strips injected price lore from an item before returning it to the player or
     * passing it to the confirm snapshot. Modifies the item in-place.
     */
    public static void stripPriceLore(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        if (!pdc.has(SELL_LORE_COUNT, PersistentDataType.INTEGER)) return;

        int origCount = pdc.getOrDefault(SELL_LORE_COUNT, PersistentDataType.INTEGER, 0);
        pdc.remove(SELL_LORE_COUNT);
        pdc.remove(SELL_LORE_AMOUNT);

        if (origCount == 0) {
            meta.setLore(null);
        } else {
            List<String> lore = meta.getLore();
            if (lore != null && lore.size() > origCount) {
                meta.setLore(new ArrayList<>(lore.subList(0, origCount)));
            }
        }
        item.setItemMeta(meta);
    }

    private double computeTotal(Player player) {
        ItemStack[] top = player.getOpenInventory().getTopInventory().getContents();
        double total = 0D;

        for (int slot = 0; slot < top.length; slot++) {
            int row = slot / 9;
            int col = slot % 9;
            if (!isSellSlot(row, col)) continue;

            ItemStack item = top[slot];
            if (item == null || item.getType() == Material.AIR) continue;

            double unit = manager.config().getUnitPrice(item);
            if (unit < 0) continue;

            total += unit * item.getAmount();
        }
        return total;
    }

    private void openConfirm(Player player, double total) {
        Map<Integer, ItemStack> snapshot = snapshotSellSlots(player);

        SmartInventory.builder()
                .id("oreo-sellgui-confirm")
                .manager(manager.getInvManager())
                .size(3, 9)
                .title(manager.config().confirmTitle())
                .provider(new SellConfirmProvider(plugin, manager, total, snapshot))
                .build()
                .open(player);
    }

    private Map<Integer, ItemStack> snapshotSellSlots(Player player) {
        Map<Integer, ItemStack> map = new HashMap<>();
        ItemStack[] top = player.getOpenInventory().getTopInventory().getContents();

        for (int slot = 0; slot < top.length; slot++) {
            int row = slot / 9;
            int col = slot % 9;
            if (!isSellSlot(row, col)) continue;

            ItemStack it = top[slot];
            if (it != null && it.getType() != Material.AIR) {
                ItemStack clone = it.clone();
                stripPriceLore(clone);
                map.put(slot, clone);
            }
        }
        return map;
    }

    private void returnAllSellItems(Player player) {
        returnSellItemsStatic(player);
    }


    public static void returnSellItemsStatic(Player player) {
        ItemStack[] top = player.getOpenInventory().getTopInventory().getContents();

        for (int slot = 0; slot < top.length; slot++) {
            int row = slot / 9;
            int col = slot % 9;
            if (!isSellSlot(row, col)) continue;

            ItemStack it = top[slot];
            if (it == null || it.getType() == Material.AIR) continue;

            top[slot] = null;

            ItemStack toReturn = it.clone();
            stripPriceLore(toReturn);
            Map<Integer, ItemStack> leftover = player.getInventory().addItem(toReturn);
            leftover.values().forEach(drop -> player.getWorld().dropItemNaturally(player.getLocation(), drop));
        }

        player.getOpenInventory().getTopInventory().setContents(top);
        player.updateInventory();
    }

    private static ItemStack button(Material material, String nameLegacy, List<String> loreLegacy) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(Lang.color(nameLegacy));

            if (loreLegacy != null && !loreLegacy.isEmpty()) {
                meta.setLore(loreLegacy.stream().map(Lang::color).toList());
            }

            meta.addItemFlags(ItemFlag.values());

            item.setItemMeta(meta);
        }
        return item;
    }
}
