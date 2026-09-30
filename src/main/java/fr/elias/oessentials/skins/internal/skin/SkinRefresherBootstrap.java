package fr.elias.oessentials.skins.internal.skin;

import fr.elias.oessentials.OEssentials;
import org.bukkit.Bukkit;

public final class SkinRefresherBootstrap {
    private SkinRefresherBootstrap() {}

    public static void init(OEssentials plugin) {
        boolean hasPE = Bukkit.getPluginManager().getPlugin("PacketEvents") != null
                || Bukkit.getPluginManager().getPlugin("packetevents") != null;
        if (hasPE) {
            try {
                SkinRefresher.Holder.set(new SkinRefresherPE(plugin));
                plugin.getLogger().info("[Skins] Using PacketEvents for live refresh.");
                return;
            } catch (Throwable t) {
                plugin.getLogger().warning("[Skins] PacketEvents present but PE path failed: " + t.getMessage());
            }
        }
        SkinRefresher.Holder.set(new SkinRefresherFallback());
        plugin.getLogger().info("[Skins] Using Bukkit hide/show fallback.");
    }
}
