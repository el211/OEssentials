package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for the EnderChest module (virtual, per-player ender chests with permission-based rows).
 * Obtain via {@link OEssentialsAPI#enderChest()}. Returns {@code null} if disabled.
 */
public interface IEnderChestAPI {

    /**
     * Opens the player's virtual ender chest GUI.
     *
     * @param player the player to open it for
     */
    void open(@NotNull Player player);

    /**
     * Loads the raw contents of a player's ender chest from storage.
     *
     * @param playerId the player's UUID
     * @param rows     the number of rows (1–6) to load
     * @return the item array
     */
    @NotNull ItemStack[] loadFor(@NotNull UUID playerId, int rows);

    /**
     * Saves item contents to storage for the given player.
     *
     * @param playerId the player's UUID
     * @param rows     the number of rows
     * @param contents the items to save
     */
    void saveFor(@NotNull UUID playerId, int rows, @NotNull ItemStack[] contents);

    /**
     * Returns how many slots this player currently has access to
     * based on their permissions.
     *
     * @param player the online player
     */
    int resolveSlots(@NotNull Player player);
}
