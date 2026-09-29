package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for the Daily Rewards module.
 * Obtain via {@link OEssentialsAPI#daily()}. Returns {@code null} if disabled.
 */
public interface IDailyAPI {

    /** Returns {@code true} if the daily rewards module is enabled. */
    boolean isEnabled();

    /**
     * Returns {@code true} if the player has not yet claimed their reward today
     * and is eligible to do so.
     */
    boolean canClaimToday(@NotNull Player player);

    /**
     * Returns the player's current claim streak (consecutive days claimed).
     *
     * @param playerId the player's UUID
     */
    int getStreak(@NotNull UUID playerId);

    /**
     * Claims the daily reward for the player.
     * This runs all reward actions (commands, items, currency) defined in config.
     *
     * @param player the player claiming the reward
     * @return {@code true} if the reward was granted, {@code false} if already claimed today or disabled
     */
    boolean claim(@NotNull Player player);
}
