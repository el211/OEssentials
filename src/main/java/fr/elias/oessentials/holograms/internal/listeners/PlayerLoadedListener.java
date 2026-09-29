package fr.elias.oessentials.holograms.internal.listeners;

import fr.elias.oessentials.holograms.internal.OHolograms;
import fr.elias.oessentials.holograms.internal.api.hologram.Hologram;
import fr.elias.oessentials.platform.scheduling.OreScheduler;
import io.papermc.paper.event.player.PlayerClientLoadedWorldEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class PlayerLoadedListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLoaded(@NotNull final PlayerClientLoadedWorldEvent event) {
        OreScheduler.runForEntity(OHolograms.get().getPlugin(), event.getPlayer(), () -> {
            for (final Hologram hologram : OHolograms.get().getHologramsManager().getHolograms()) {
                hologram.forceUpdate();
                hologram.forceUpdateShownStateFor(event.getPlayer());
                if (hologram.isViewer(event.getPlayer())) {
                    hologram.refreshHologram(event.getPlayer());
                }
            }
        });
    }

}
