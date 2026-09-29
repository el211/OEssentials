package fr.elias.oreoEssentials.holograms.internal.api;

import fr.elias.oreoEssentials.api.IHologramsAPI;
import fr.elias.oreoEssentials.holograms.internal.api.HologramManager;
import fr.elias.oreoEssentials.holograms.internal.api.hologram.Hologram;
import fr.elias.oreoEssentials.holograms.internal.api.data.HologramData;
import fr.elias.oreoEssentials.holograms.internal.HologramManagerImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public class HologramsAPIImpl implements IHologramsAPI {
    private final HologramManager mgr;
    public HologramsAPIImpl(HologramManager mgr) { this.mgr = mgr; }

    @Override public @NotNull Optional<Hologram> getHologram(@NotNull String name) { return mgr.getHologram(name); }
    @Override public @NotNull Collection<Hologram> getPersistentHolograms() { return mgr.getPersistentHolograms(); }
    @Override public @NotNull Collection<Hologram> getHolograms() { return mgr.getHolograms(); }
    @Override public @NotNull Hologram create(@NotNull HologramData data) { return mgr.create(data); }
    @Override public @NotNull Optional<Hologram> removeHologram(@NotNull String name) {
        if (mgr instanceof HologramManagerImpl impl) return impl.removeHologram(name);
        return Optional.empty();
    }
    @Override public void saveHolograms() { mgr.saveHolograms(); }
    @Override public void reloadHolograms() { mgr.reloadHolograms(); }
    @Override public boolean isLoaded() { return mgr.isLoaded(); }
}
