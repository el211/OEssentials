package fr.elias.oessentials.platform.modularity;

import fr.elias.oessentials.OEssentials;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;

public final class BootstrapSupport {
    private BootstrapSupport() {}

    @SuppressWarnings("unchecked")
    public static void unregisterCommandHard(OEssentials plugin, String label) {
        try {
            Object craftServer = plugin.getServer();
            org.bukkit.command.CommandMap commandMap = null;
            try {
                var m = craftServer.getClass().getMethod("getCommandMap");
                Object res = m.invoke(craftServer);
                if (res instanceof org.bukkit.command.CommandMap cm) commandMap = cm;
            } catch (Throwable ignored) {}
            if (commandMap == null) {
                var f = craftServer.getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                Object res = f.get(craftServer);
                if (res instanceof org.bukkit.command.CommandMap cm) commandMap = cm;
            }
            if (!(commandMap instanceof org.bukkit.command.SimpleCommandMap map)) return;
            var f2 = org.bukkit.command.SimpleCommandMap.class.getDeclaredField("knownCommands");
            f2.setAccessible(true);
            Map<String, org.bukkit.command.Command> known = (Map<String, Command>) f2.get(map);
            String lower = label.toLowerCase(java.util.Locale.ROOT);
            known.entrySet().removeIf(e -> {
                String k = e.getKey().toLowerCase(java.util.Locale.ROOT);
                return k.equals(lower) || k.endsWith(":" + lower);
            });
        } catch (Throwable ignored) {}
    }

    public static boolean uiModuleAllowed(OEssentials plugin, String moduleKey) {
        String server = plugin.getConfigService() != null ? plugin.getConfigService().serverName() : Bukkit.getServer().getName();
        boolean disabled = plugin.getSettingsConfig() != null && plugin.getSettingsConfig().uiModuleDisabledForServer(server, moduleKey);
        if (disabled) {
            plugin.getLogger().info("[UI Safeguard] Disabled " + moduleKey + " on server " + server + ".");
        }
        return !disabled;
    }
}
