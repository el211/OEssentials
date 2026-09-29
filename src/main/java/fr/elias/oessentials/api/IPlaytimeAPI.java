package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * API for the Playtime module — playtime tracking and rewards.
 *
 * <p>Obtain via {@link OEssentialsAPI#playtime()}. Returns {@code null} if disabled.
 */
public interface IPlaytimeAPI {

    /**
     * Returns the total tracked playtime for a player in seconds.
     *
     * @param playerId the player's UUID
     */
    long getSeconds(@NotNull UUID playerId);

    /**
     * Returns {@code true} if the playtime-rewards feature is enabled.
     */
    boolean isRewardsEnabled();

    /**
     * Returns a list of reward IDs that the player is eligible to claim right now.
     *
     * @param player an online player
     */
    @NotNull List<String> rewardsReady(@NotNull Player player);

    /**
     * Claims a specific playtime reward for the player.
     *
     * @param player the online player
     * @param rewardId the reward identifier
     * @return {@code true} if the reward was successfully claimed
     */
    boolean claim(@NotNull Player player, @NotNull String rewardId);
}
