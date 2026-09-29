package fr.elias.oessentials.inventory.internal.auctionhouse.models;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum AuctionCategory {
    ALL("All Items",        Material.CHEST,              "oreo.ah.category.all"),
    BLOCKS("Blocks",        Material.BRICKS,             "oreo.ah.category.blocks"),
    TOOLS("Tools",          Material.DIAMOND_PICKAXE,    "oreo.ah.category.tools"),
    WEAPONS("Weapons",      Material.DIAMOND_SWORD,      "oreo.ah.category.weapons"),
    ARMOR("Armor",          Material.DIAMOND_CHESTPLATE,  "oreo.ah.category.armor"),
    FOOD("Food",            Material.COOKED_BEEF,        "oreo.ah.category.food"),
    POTIONS("Potions",      Material.POTION,             "oreo.ah.category.potions"),
    SPAWNERS("Spawners",    Material.SPAWNER,            "oreo.ah.category.spawners"),
    CUSTOM("Custom Items",  Material.NETHER_STAR,        "oreo.ah.category.custom"),
    MISC("Miscellaneous",   Material.PAPER,              "oreo.ah.category.misc");

    private final String displayName;
    private final Material icon;
    private final String permission;

    AuctionCategory(String displayName, Material icon, String permission) {
        this.displayName = displayName;
        this.icon = icon;
        this.permission = permission;
    }

    public String getDisplayName() { return displayName; }
    public Material getIcon()      { return icon; }
    public String getPermission()  { return permission; }

    public static AuctionCategory fromMaterial(Material material) {
        if (material == null) return MISC;

        if (material == Material.SPAWNER) return SPAWNERS;
        String name = material.name();
        if (name.endsWith("_SPAWN_EGG")) return SPAWNERS;

        if (name.contains("SWORD")
                || name.contains("BOW")
                || name.contains("CROSSBOW")
                || name.contains("TRIDENT")
                || name.equals("MACE")) {
            return WEAPONS;
        }

        if (name.contains("HELMET")
                || name.contains("CHESTPLATE")
                || name.contains("LEGGINGS")
                || name.contains("BOOTS")
                || name.equals("SHIELD")) {
            return ARMOR;
        }

        if (name.contains("PICKAXE")
                || name.contains("AXE")
                || name.contains("SHOVEL")
                || name.contains("HOE")
                || name.equals("SHEARS")
                || name.equals("FISHING_ROD")
                || name.equals("FLINT_AND_STEEL")
                || name.equals("BRUSH")) {
            return TOOLS;
        }

        if (material.isEdible()) return FOOD;

        if (name.contains("POTION") || name.equals("TIPPED_ARROW")) return POTIONS;

        if (material.isBlock()) return BLOCKS;

        return MISC;
    }
    public static AuctionCategory fromItem(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return MISC;

        if (isCustom(item)) return CUSTOM;

        return fromMaterial(item.getType());
    }

    private static boolean isCustom(ItemStack item) {
        try {
            if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) return true;
        } catch (Throwable ignored) {}

        try {
            Class<?> cs = Class.forName("dev.lone.itemsadder.api.CustomStack");
            Object stack = cs.getMethod("byItemStack", ItemStack.class).invoke(null, item);
            if (stack != null) return true;
        } catch (Throwable ignored) {}

        try {
            Class<?> nx = Class.forName("com.nexomc.nexo.api.NexoItems");
            Object exists = nx.getMethod("exists", ItemStack.class).invoke(null, item);
            if (Boolean.TRUE.equals(exists)) return true;
        } catch (Throwable ignored) {}

        try {
            Class<?> ox = Class.forName("io.th0rgal.oraxen.api.OraxenItems");
            Object id = ox.getMethod("getIdByItem", ItemStack.class).invoke(null, item);
            if (id != null) return true;
        } catch (Throwable ignored) {}

        return false;
    }

}