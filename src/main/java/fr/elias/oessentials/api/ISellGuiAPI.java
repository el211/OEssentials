package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the SellGUI module (bulk-sell inventory GUI).
 * Obtain via {@link OEssentialsAPI#sellGui()}. Returns {@code null} if disabled.
 */
public interface ISellGuiAPI {

    /**
     * Opens the sell GUI for the given player.
     *
     * @param player the player to open it for
     */
    void openSell(@NotNull Player player);

    /** Reloads the SellGUI configuration. */
    void reload();
}
