package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the TempFly module — time-limited flight.
 *
 * <p>Obtain via {@link OEssentialsAPI#tempFly()}. Returns {@code null} if disabled.
 */
public interface ITempFlyAPI {

    /**
     * Grants temporary flight to the player.
     *
     * @param player the online player
     */
    void enableFly(@NotNull Player player);

    /**
     * Revokes temporary flight from the player.
     *
     * @param player the online player
     */
    void disableFly(@NotNull Player player);

    /**
     * Returns the remaining flight time for the player in seconds,
     * or {@code 0} if the player has no active temp-fly session.
     *
     * @param player the online player
     */
    int getTimeRemaining(@NotNull Player player);
}
