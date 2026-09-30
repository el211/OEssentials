package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for the Vanish module — staff invisibility.
 *
 * <p>Obtain via {@link OEssentialsAPI#vanish()}. Returns {@code null} if disabled.
 */
public interface IVanishAPI {

    /**
     * Returns {@code true} if the online player is currently vanished.
     */
    boolean isVanished(@NotNull Player player);

    /**
     * Returns {@code true} if the player with the given UUID is currently vanished.
     * Works for both online and cached offline players.
     */
    boolean isVanished(@NotNull UUID playerId);

    /**
     * Toggles vanish state for the player.
     *
     * @return the new vanish state ({@code true} = now vanished)
     */
    boolean toggle(@NotNull Player player);

    /**
     * Explicitly sets the vanish state for the player.
     *
     * @param player  the online player
     * @param vanish  {@code true} to vanish, {@code false} to unvanish
     * @return the resulting vanish state
     */
    boolean setVanished(@NotNull Player player, boolean vanish);
}
