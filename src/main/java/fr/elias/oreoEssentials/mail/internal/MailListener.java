package fr.elias.oreoEssentials.mail.internal;

import fr.elias.oreoEssentials.mail.internal.rabbitmq.MailDeliveryPacket;
import fr.elias.oreoEssentials.platform.scheduling.OreScheduler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

/**
 * Handles:
 *  - Notifying players of unread mail on join.
 *  - Delivering pending broadcasts to players on join.
 *  - Processing incoming {@link MailDeliveryPacket}s from other servers.
 */
public final class MailListener implements Listener {

    private final Plugin      plugin;
    private final MailService mail;

    public MailListener(Plugin plugin, MailService mail) {
        this.plugin = plugin;
        this.mail   = mail;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        // Check broadcasts first (this may add new mails to the player's box)
        mail.checkPendingBroadcasts(player.getUniqueId());

        // Notify of unread mail after a short delay so it's not buried in join messages
        OreScheduler.runLater(plugin, () -> {
            if (!player.isOnline()) return;
            mail.unreadCountAsync(player.getUniqueId()).thenAccept(count -> {
                if (count > 0) {
                    OreScheduler.runForEntity(plugin, player, () -> {
                        if (!player.isOnline()) return;
                        player.sendMessage(
                                "§6[Mail] §eYou have §f" + count + " §eunread mail(s). "
                                + "Use §f/mail §eto open your mailbox.");
                    });
                }
            });
        }, 20L); // 1 second delay
    }

    /**
     * Called from {@code OreoEssentials.initRabbitMQ()} when a
     * {@link MailDeliveryPacket} arrives from another server.
     */
    public void onMailDelivery(MailDeliveryPacket packet) {
        Player target = org.bukkit.Bukkit.getPlayer(packet.getTargetUuid());
        if (target == null || !target.isOnline()) return;
        OreScheduler.runForEntity(plugin, target, () -> {
            if (!target.isOnline()) return;
            MailService.sendNotification(target, packet.getSenderName(),
                    packet.isHasItem(), packet.isBroadcast());
        });
    }
}
