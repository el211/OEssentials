package fr.elias.oessentials.api;

import fr.elias.oessentials.storage.internal.playerwarp.PlayerWarp;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * API for the Player Warps module.
 * Obtain via {@link OEssentialsAPI#playerWarps()}. Returns {@code null} if disabled.
 */
public interface IPlayerWarpsAPI {

    /**
     * Creates a new player warp.
     *
     * @param owner    the owning player
     * @param name     the warp name
     * @param location the warp location
     * @return the created warp, or {@code null} if creation failed
     */
    @Nullable PlayerWarp createWarp(@NotNull Player owner, @NotNull String name, @NotNull Location location);

    /**
     * Deletes a player warp.
     *
     * @param warp the warp to delete
     * @return {@code true} if deleted
     */
    boolean deleteWarp(@NotNull PlayerWarp warp);

    /**
     * Finds a player's warp by owner and name.
     *
     * @param ownerId the owner's UUID
     * @param name    the warp name
     * @return the warp, or {@code null} if not found
     */
    @Nullable PlayerWarp findByOwnerAndName(@NotNull UUID ownerId, @NotNull String name);

    /**
     * Returns all warps owned by the given player.
     *
     * @param ownerId the owner's UUID
     */
    @NotNull List<PlayerWarp> listByOwner(@NotNull UUID ownerId);

    /** Returns all player warps on this server. */
    @NotNull List<PlayerWarp> listAll();

    /**
     * Returns how many warps the player has created.
     *
     * @param owner the player
     */
    int getWarpCount(@NotNull Player owner);

    /**
     * Returns {@code true} if the player can use the given warp
     * (permission + whitelist check).
     */
    boolean canUse(@NotNull Player player, @NotNull PlayerWarp warp);

    /**
     * Teleports the player to a specific player warp.
     *
     * @param player    the player to teleport
     * @param ownerId   the warp owner's UUID
     * @param warpName  the warp name
     * @return {@code true} if teleportation was initiated
     */
    boolean teleportTo(@NotNull Player player, @NotNull UUID ownerId, @NotNull String warpName);
}
