package fr.elias.oessentials.api;

import fr.elias.oessentials.commands.internal.ic.ICEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * API for the Interactive Commands (IC) module.
 *
 * <p>Obtain via {@link OEssentialsAPI#interactiveCommands()}. Returns {@code null} if disabled.
 */
public interface IInteractiveCommandsAPI {

    /**
     * Returns the IC entry for the given name, or {@code null} if not found.
     *
     * @param name the command name (case-insensitive)
     */
    @Nullable ICEntry get(@NotNull String name);

    /**
     * Creates a new empty IC entry with the given name.
     *
     * @param name the command name
     * @return the newly created entry
     */
    @NotNull ICEntry create(@NotNull String name);

    /**
     * Returns all registered IC entries.
     */
    @NotNull Collection<ICEntry> all();

    /**
     * Reloads all IC entries from disk.
     */
    void reload();

    /**
     * Saves all IC entries to disk.
     */
    void save();
}
