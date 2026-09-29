package fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands;

import fr.elias.oreoEssentials.inventory.internal.auctionhouse.AuctionHouseModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AdminCommand implements CommandExecutor {

    private static final long CLEAR_CONFIRM_WINDOW_MS = 15_000L;

    private final Map<String, Long> pendingClearConfirm = new ConcurrentHashMap<>();

    private final AuctionHouseModule module;

    public AdminCommand(AuctionHouseModule module) {
        this.module = module;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String[] args) {

        if (!sender.hasPermission("oreo.ah.admin")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§e/ahadmin reload §7- Reload auction house config");
            sender.sendMessage("§e/ahadmin save   §7- Force-save auctions");
            sender.sendMessage("§e/ahadmin clear  §7- Clear all active auctions (requires confirm)");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> {
                module.reload();
                sender.sendMessage("§aAuction house config reloaded.");
            }
            case "save" -> {
                module.saveAuctions();
                sender.sendMessage("§aAuctions saved.");
            }
            case "clear" -> handleClear(sender, args);
            default -> sender.sendMessage("§cUnknown sub-command.");
        }

        return true;
    }

    private void handleClear(CommandSender sender, String[] args) {
        String key = senderKey(sender);
        long now = System.currentTimeMillis();

        if (args.length >= 2 && args[1].equalsIgnoreCase("confirm")) {
            Long startedAt = pendingClearConfirm.get(key);
            if (startedAt == null) {
                sender.sendMessage("§cNo pending clear request. Type §e/ahadmin clear§c first.");
                return;
            }
            if (now - startedAt > CLEAR_CONFIRM_WINDOW_MS) {
                pendingClearConfirm.remove(key);
                sender.sendMessage("§cConfirmation expired. Type §e/ahadmin clear§c again.");
                return;
            }

            pendingClearConfirm.remove(key);

            int cleared = module.clearAllAuctions();
            sender.sendMessage("§aCleared §e" + cleared + " §aactive auctions.");
            return;
        }

        pendingClearConfirm.put(key, now);
        sender.sendMessage("§c⚠ This will delete ALL active auctions.");
        sender.sendMessage("§eType §6/ahadmin clear confirm §ewithin §6" + (CLEAR_CONFIRM_WINDOW_MS / 1000) + "s §eto proceed.");
    }

    private String senderKey(CommandSender sender) {

        return sender.getName().toLowerCase();
    }
}
