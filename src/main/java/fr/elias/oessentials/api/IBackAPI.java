package fr.elias.oessentials.api;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * API for the /back module — last-location tracking.
 *
 * <p>Obtain via {@link OEssentialsAPI#back()}. Returns {@code null} if disabled.
 */
public interface IBackAPI {

    /**
     * Returns the last local-server {@link Location} for the player,
     * or {@code null} if none is recorded or the last location was on a different server.
     *
     * @param playerId the player's UUID
     */
    @Nullable Location getLastLocal(@NotNull UUID playerId);

    /**
     * Sets the last location for a player.
     *
     * @param playerId the player's UUID
     * @param location the location to store
     */
    void setLast(@NotNull UUID playerId, @NotNull Location location);
}
