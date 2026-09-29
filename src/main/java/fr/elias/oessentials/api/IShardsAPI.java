package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * API for the Shards module — seamless world sharding across servers.
 *
 * <p>Obtain via {@link OEssentialsAPI#shards()}. Returns {@code null} if the
 * shards module is not loaded.
 */
public interface IShardsAPI {

    /**
     * Returns {@code true} if the sharding system is active (Redis connected, enabled in config).
     */
    boolean isEnabled();

    /**
     * Returns the shard ID this server is running as (e.g. {@code "shard-0-0"}).
     */
    @Nullable String getCurrentShardId();

    /**
     * Checks whether the given world-space position is near a shard border and,
     * if so, returns the target shard server name. Returns {@code null} if within
     * the current shard's bounds.
     *
     * @param worldName the world name
     * @param x         X coordinate
     * @param z         Z coordinate
     * @param buffer    border buffer in blocks
     */
    @Nullable String getTargetShardAtBorder(@NotNull String worldName, double x, double z, int buffer);

    /**
     * Transfers the player to a different shard server.
     *
     * @param player      the online player to transfer
     * @param targetShard the shard server name to transfer to
     */
    void transferPlayerToShard(@NotNull Player player, @NotNull String targetShard);

    /**
     * Returns {@code true} if the given world-space position is within a safe zone
     * (near a shard border where combat is blocked).
     *
     * @param worldName the world name
     * @param x         X coordinate
     * @param z         Z coordinate
     */
    boolean isInSafeZone(@NotNull String worldName, double x, double z);
}
