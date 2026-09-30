package fr.elias.oessentials.api;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * API for the DeathBack module — death-location tracking.
 *
 * <p>Obtain via {@link OEssentialsAPI#deathBack()}. Returns {@code null} if disabled.
 */
public interface IDeathBackAPI {

    /**
     * Returns the last recorded death location for the player, or {@code null} if none.
     *
     * @param playerId the player's UUID
     */
    @Nullable Location getLastDeath(@NotNull UUID playerId);

    /**
     * Manually sets (or overrides) the death location for a player.
     *
     * @param playerId the player's UUID
     * @param location the death location
     */
    void setLastDeath(@NotNull UUID playerId, @NotNull Location location);

    /**
     * Clears the stored death location for a player.
     *
     * @param playerId the player's UUID
     */
    void clear(@NotNull UUID playerId);
}
