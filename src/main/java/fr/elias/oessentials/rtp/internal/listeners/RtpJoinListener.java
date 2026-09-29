package fr.elias.oessentials.rtp.internal.listeners;

import fr.elias.oessentials.OEssentials;
import fr.elias.oessentials.rtp.internal.RtpCommand;
import fr.elias.oessentials.platform.scheduling.OreScheduler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RtpJoinListener implements Listener {

    private final OEssentials plugin;

    public RtpJoinListener(OEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        OreScheduler.runLaterForEntity(plugin, p, () -> {
            String worldName = plugin.getRtpPendingService().consume(p.getUniqueId());
            if (worldName == null || worldName.isBlank()) return;

            plugin.getLogger().info("[RTP] Executing pending RTP for " + p.getName() + " in world=" + worldName);

            RtpCommand.doLocalRtp(plugin, p, worldName, true);
        }, 1L);
    }
}
