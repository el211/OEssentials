package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the Scoreboard module — animated sidebar scoreboards.
 *
 * <p>Obtain via {@link OEssentialsAPI#scoreboard()}. Returns {@code null} if disabled.
 */
public interface IScoreboardAPI {

    /**
     * Returns {@code true} if the scoreboard is currently shown to the player.
     */
    boolean isShown(@NotNull Player player);

    /**
     * Toggles scoreboard visibility for the player.
     */
    void toggle(@NotNull Player player);

    /**
     * Shows the scoreboard to the player.
     */
    void show(@NotNull Player player);

    /**
     * Hides the scoreboard from the player.
     */
    void hide(@NotNull Player player);

    /**
     * Reloads scoreboard configuration from disk and restarts the update cycle.
     */
    void reload();
}
