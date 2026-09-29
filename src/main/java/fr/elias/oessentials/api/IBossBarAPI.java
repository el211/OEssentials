package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the BossBar module.
 * Obtain via {@link OEssentialsAPI#bossBar()}. Returns {@code null} if disabled.
 */
public interface IBossBarAPI {

    /** Returns {@code true} if the bossbar is currently shown to the player. */
    boolean isShown(@NotNull Player player);

    /** Shows the bossbar to the given player. */
    void show(@NotNull Player player);

    /** Hides the bossbar from the given player. */
    void hide(@NotNull Player player);

    /** Reloads the bossbar configuration. */
    void reload();
}
