package fr.elias.oessentials.notes.internal.notes;

import fr.elias.oessentials.OEssentials;
import fr.elias.oessentials.shared.Lang;
import fr.elias.oessentials.platform.scheduling.OreScheduler;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NotesChatListener implements Listener {

    private final OEssentials plugin;
    private final PlayerNotesManager manager;
    private final Map<UUID, UUID> pending = new ConcurrentHashMap<>();

    public NotesChatListener(OEssentials plugin, PlayerNotesManager manager) {
        this.plugin = plugin;
        this.manager = manager;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void startNote(Player staff, UUID target) {
        pending.put(staff.getUniqueId(), target);
        Lang.send(staff, "modgui.notes.prompt",
                "<yellow>Type the note in chat.</yellow> <gray>(Or type 'cancel' to abort)</gray>",
                Map.of());
    }

    /** Returns true if this player is currently being prompted to type a note. */
    public boolean isWaitingForNote(UUID uuid) {
        return pending.containsKey(uuid);
    }

    @SuppressWarnings("UnstableApiUsage")
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
    public void onChat(AsyncChatEvent e) {
        UUID staffId = e.getPlayer().getUniqueId();
        if (!pending.containsKey(staffId)) return;

        e.setCancelled(true);
        UUID target = pending.remove(staffId);
        String msg = PlainTextComponentSerializer.plainText().serialize(e.message());

        if (msg.equalsIgnoreCase("cancel")) {
            Lang.send(e.getPlayer(), "modgui.notes.cancelled",
                    "<red>Note cancelled.</red>",
                    Map.of());
            return;
        }

        String staffName = e.getPlayer().getName();
        Player player = e.getPlayer();

        OreScheduler.runForEntity(plugin, player, () -> {
            manager.addNote(target, staffName, msg);
            Lang.send(player, "modgui.notes.added",
                    "<green>Note added.</green>",
                    Map.of());
        });
    }
}