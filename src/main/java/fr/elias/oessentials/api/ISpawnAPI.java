package fr.elias.oessentials.api;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * API for the Spawn module.
 * Obtain via {@link OEssentialsAPI#spawn()}. Returns {@code null} if disabled.
 */
public interface ISpawnAPI {

    /**
     * Returns the configured spawn location for this server, or {@code null} if not set.
     */
    @Nullable Location getSpawn();

    /**
     * Sets the spawn location for this server.
     *
     * @param location the new spawn location
     */
    void setSpawn(@NotNull Location location);

    /**
     * Returns the local spawn (this server only), or {@code null} if not set.
     */
    @Nullable Location getLocalSpawn();

    /**
     * Sets the local spawn (this server only).
     *
     * @param location the new local spawn
     */
    void setLocalSpawn(@NotNull Location location);

    /**
     * Returns the global spawn shared across all servers, or {@code null} if not set.
     */
    @Nullable Location getGlobalSpawn();

    /**
     * Sets the global spawn broadcast to all servers.
     *
     * @param location the new global spawn
     */
    void setGlobalSpawn(@NotNull Location location);

    /**
     * Returns the first-join spawn location, or {@code null} if not set.
     */
    @Nullable Location getFirstJoinSpawn();

    /**
     * Sets the spawn location used when a player joins the server for the first time.
     *
     * @param location the new first-join spawn
     */
    void setFirstJoinSpawn(@NotNull Location location);
}
