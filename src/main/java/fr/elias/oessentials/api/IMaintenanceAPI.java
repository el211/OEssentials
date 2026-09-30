package fr.elias.oessentials.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * API for the Maintenance module.
 * Obtain via {@link OEssentialsAPI#maintenance()}. Returns {@code null} if disabled.
 */
public interface IMaintenanceAPI {

    /** Returns {@code true} if the server is currently in maintenance mode. */
    boolean isEnabled();

    /** Enables maintenance mode. */
    void enable();

    /** Disables maintenance mode. */
    void disable();

    /** Toggles maintenance mode and returns the new state. */
    boolean toggle();

    /**
     * Returns {@code true} if the given player is allowed to join during maintenance
     * (either whitelisted or has bypass permission).
     */
    boolean canJoin(@NotNull org.bukkit.entity.Player player);

    /** Adds a player to the maintenance whitelist by UUID. */
    void addToWhitelist(@NotNull UUID uuid);

    /** Removes a player from the maintenance whitelist by UUID. */
    void removeFromWhitelist(@NotNull UUID uuid);

    /** Clears the entire maintenance whitelist. */
    void clearWhitelist();

    /**
     * Kicks all online players who are not on the whitelist.
     * Useful to call immediately after enabling maintenance.
     */
    void kickNonWhitelistedPlayers();

    /**
     * Sets a countdown timer after which maintenance is automatically disabled.
     *
     * @param durationMillis duration in milliseconds
     */
    void setTimer(long durationMillis);

    /** Removes any active maintenance timer. */
    void removeTimer();

    /**
     * Returns a formatted string showing the time remaining in the maintenance timer,
     * or {@code null} if no timer is active.
     */
    @Nullable String getFormattedTimeRemaining();
}
