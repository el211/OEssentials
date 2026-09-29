package fr.elias.oreoEssentials.grouprtp.internal.listener;

import fr.elias.oreoEssentials.grouprtp.internal.command.GroupRtpCommand;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Handles left/right click with the GroupRTP selection wand.
 */
public final class GroupRtpWandListener implements Listener {

    private final GroupRtpCommand command;

    public GroupRtpWandListener(GroupRtpCommand command) {
        this.command = command;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!GroupRtpCommand.isWand(e.getItem())) return;
        e.setCancelled(true);

        var p = e.getPlayer();
        if (!p.hasPermission("oreo.grouprtp.admin")) return;

        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
            command.setPos(p.getUniqueId(), 1, p.getLocation());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&aPos1 set to &e" + blockStr(p.getLocation())));
        } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            command.setPos(p.getUniqueId(), 2, p.getLocation());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&aPos2 set to &e" + blockStr(p.getLocation())));
        }
    }

    private static String blockStr(org.bukkit.Location loc) {
        return loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ();
    }
}
