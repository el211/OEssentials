package fr.elias.oessentials.api;

import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

/**
 * API for the Ignore module — player ignore lists.
 *
 * <p>Obtain via {@link OEssentialsAPI#ignore()}. Returns {@code null} if disabled.
 */
public interface IIgnoreAPI {

    /**
     * Returns {@code true} if {@code player} is ignoring {@code target}.
     */
    boolean isIgnoring(@NotNull UUID player, @NotNull UUID target);

    /**
     * Adds {@code target} to {@code player}'s ignore list.
     *
     * @return {@code true} if the entry was newly added (was not already ignored)
     */
    boolean ignore(@NotNull UUID player, @NotNull UUID target);

    /**
     * Removes {@code target} from {@code player}'s ignore list.
     *
     * @return {@code true} if the entry was removed (was previously ignored)
     */
    boolean unignore(@NotNull UUID player, @NotNull UUID target);

    /**
     * Returns an unmodifiable view of the set of UUIDs ignored by {@code player}.
     */
    @NotNull Set<UUID> getIgnoredBy(@NotNull UUID player);

    /**
     * Clears the entire ignore list for {@code player}.
     */
    void clearIgnoreList(@NotNull UUID player);
}
