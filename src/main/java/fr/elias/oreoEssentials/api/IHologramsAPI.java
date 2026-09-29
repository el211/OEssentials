package fr.elias.oreoEssentials.api;

import fr.elias.oreoEssentials.holograms.internal.api.hologram.Hologram;
import fr.elias.oreoEssentials.holograms.internal.api.data.HologramData;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * API for the Holograms module (OHolograms).
 *
 * <p>This is a thin delegate over the existing {@link fr.elias.oreoEssentials.holograms.internal.api.HologramManager}
 * interface, which also has its own rich event system in
 * {@code fr.elias.oreoEssentials.holograms.internal.api.events}.
 *
 * <p>Obtain via {@link OreoEssentialsAPI#holograms()}. Returns {@code null} if OHolograms is disabled.
 */
public interface IHologramsAPI {

    /**
     * Returns a hologram by name.
     *
     * @param name the hologram name
     */
    @NotNull Optional<Hologram> getHologram(@NotNull String name);

    /**
     * Returns all persistent (saved) holograms.
     */
    @NotNull Collection<Hologram> getPersistentHolograms();

    /**
     * Returns all currently active holograms (persistent + temporary).
     */
    @NotNull Collection<Hologram> getHolograms();

    /**
     * Creates and registers a new hologram from the given data.
     *
     * @param data the hologram configuration
     * @return the created hologram
     */
    @NotNull Hologram create(@NotNull HologramData data);

    /**
     * Removes a hologram by name.
     *
     * @param name the hologram name
     * @return the removed hologram, or empty if not found
     */
    @NotNull Optional<Hologram> removeHologram(@NotNull String name);

    /** Saves all persistent holograms to disk. */
    void saveHolograms();

    /** Reloads all holograms from disk. */
    void reloadHolograms();

    /** Returns {@code true} if holograms have been loaded. */
    boolean isLoaded();
}
