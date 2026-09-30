package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for the AFK module.
 * Obtain via {@link OEssentialsAPI#afk()}. Returns {@code null} if disabled.
 */
public interface IAfkAPI {

    /** Returns {@code true} if the given player is currently AFK. */
    boolean isAfk(@NotNull Player player);

    /** Returns how many seconds the player has been AFK, or 0 if not AFK. */
    long getAfkForSeconds(@NotNull Player player);

    /** Returns how many seconds since the player last had any activity. */
    long getInactiveForSeconds(@NotNull Player player);

    /**
     * Toggles the AFK state of the player.
     * @return the new AFK state
     */
    boolean toggleAfk(@NotNull Player player);

    /**
     * Explicitly sets the AFK state of the player.
     *
     * @param player the player
     * @param afk    {@code true} to mark as AFK, {@code false} to clear
     */
    void setAfk(@NotNull Player player, boolean afk);

    /**
     * Clears the AFK state for the player (equivalent to {@code setAfk(player, false)}).
     */
    void clearAfk(@NotNull Player player);
}
