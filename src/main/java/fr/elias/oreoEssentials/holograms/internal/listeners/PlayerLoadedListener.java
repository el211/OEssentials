package fr.elias.oreoEssentials.holograms.internal.listeners;

import fr.elias.oreoEssentials.holograms.internal.OHolograms;
import fr.elias.oreoEssentials.holograms.internal.api.hologram.Hologram;
import fr.elias.oreoEssentials.platform.scheduling.OreScheduler;
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
