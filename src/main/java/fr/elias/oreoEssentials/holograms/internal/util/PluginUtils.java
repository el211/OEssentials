package fr.elias.oreoEssentials.holograms.internal.util;

import org.bukkit.Bukkit;

public class PluginUtils {

    public static boolean isFancyNpcsEnabled() {
        return Bukkit.getPluginManager().getPlugin("FancyNpcs") != null;
    }

    public static boolean isFloodgateEnabled() {
        return Bukkit.getPluginManager().getPlugin("floodgate") != null;
    }

    public static boolean isViaVersionEnabled() {
        return Bukkit.getPluginManager().getPlugin("ViaVersion") != null;
    }
}
