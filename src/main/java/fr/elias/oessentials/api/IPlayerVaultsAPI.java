package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the Player Vaults module (personal storage vaults).
 * Obtain via {@link OEssentialsAPI#playerVaults()}. Returns {@code null} if disabled.
 */
public interface IPlayerVaultsAPI {

    /** Returns {@code true} if the player vaults module is enabled. */
    boolean isEnabled();

    /**
     * Opens the vault selection menu for the player.
     *
     * @param player the player
     */
    void openMenu(@NotNull Player player);

    /**
     * Opens a specific vault for the player.
     *
     * @param player  the player
     * @param vaultId the vault ID (1-based)
     */
    void openVault(@NotNull Player player, int vaultId);

    /**
     * Returns {@code true} if the player has permission to access the given vault ID.
     *
     * @param player  the player
     * @param vaultId the vault ID
     */
    boolean canAccess(@NotNull Player player, int vaultId);

    /**
     * Returns how many item slots this player has available in the given vault,
     * based on their permissions.
     *
     * @param player  the player
     * @param vaultId the vault ID
     */
    int resolveSlots(@NotNull Player player, int vaultId);
}
