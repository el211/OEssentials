package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the Nametag module.
 *
 * <p>Obtain via {@link OEssentialsAPI#nametag()}. Returns {@code null} if the
 * nametag module is disabled.
 *
 * <h2>Example — force a nametag refresh after a rank change</h2>
 * <pre>{@code
 * INametagAPI nametag = OEssentialsAPI.get().nametag();
 * if (nametag != null) {
 *     nametag.forceUpdate(player);
 * }
 * }</pre>
 */
public interface INametagAPI {

    /**
     * Returns {@code true} if the nametag module is active.
     */
    boolean isEnabled();

    /**
     * Updates the nametag of a single player for all viewers.
     * Use this after changing a player's prefix/suffix or rank.
     *
     * @param player the player whose nametag should be refreshed
     */
    void updateNametag(@NotNull Player player);

    /**
     * Forces an immediate nametag refresh for a player, bypassing any
     * debounce or cooldown logic.
     *
     * @param player the player to refresh
     */
    void forceUpdate(@NotNull Player player);

    /**
     * Forces a nametag refresh for every online player.
     * Useful after a global rank or prefix change.
     */
    void forceUpdateAll();

    /**
     * Removes / hides the nametag for a specific player.
     * Useful for vanish or disguise integrations.
     *
     * @param player the player whose nametag should be hidden
     */
    void disableNametag(@NotNull Player player);
}
