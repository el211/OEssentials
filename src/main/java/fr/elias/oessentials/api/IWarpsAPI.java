package fr.elias.oessentials.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * API for the Warps module.
 * Obtain via {@link OEssentialsAPI#warps()}. Returns {@code null} if disabled.
 */
public interface IWarpsAPI {

    /** Returns the names of all configured warps. */
    @NotNull Set<String> listWarps();

    /**
     * Returns the location of the named warp, or {@code null} if it doesn't exist.
     *
     * @param name the warp name (case-insensitive)
     */
    @Nullable Location getWarp(@NotNull String name);

    /**
     * Creates or overwrites a warp.
     *
     * @param name     the warp name
     * @param location the warp location
     * @return {@code true} if saved successfully
     */
    boolean setWarp(@NotNull String name, @NotNull Location location);

    /**
     * Deletes a warp.
     *
     * @param name the warp name
     * @return {@code true} if it existed and was removed
     */
    boolean delWarp(@NotNull String name);

    /**
     * Returns {@code true} if the player has permission to use the given warp.
     *
     * @param player the player to check
     * @param name   the warp name
     */
    boolean canUse(@NotNull Player player, @NotNull String name);

    /**
     * Renames a warp.
     *
     * @param oldName current warp name
     * @param newName new warp name
     * @return {@code true} if renamed successfully
     */
    boolean renameWarp(@NotNull String oldName, @NotNull String newName);
}
