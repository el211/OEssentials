package fr.elias.oessentials.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the Teleport module (TPA, silent teleport).
 * Obtain via {@link OEssentialsAPI#teleport()}. Returns {@code null} if disabled.
 */
public interface ITeleportAPI {

    /**
     * Sends a TPA request from {@code from} to {@code to}.
     *
     * @param from the player requesting to teleport
     * @param to   the target player
     * @return {@code true} if the request was sent
     */
    boolean request(@NotNull Player from, @NotNull Player to);

    /**
     * Accepts the pending TPA request for the given player.
     *
     * @param target the player accepting
     * @return {@code true} if there was a request to accept
     */
    boolean accept(@NotNull Player target);

    /**
     * Denies the pending TPA request for the given player.
     *
     * @param target the player denying
     * @return {@code true} if there was a request to deny
     */
    boolean deny(@NotNull Player target);

    /**
     * Silently teleports the player to a location without any messages or effects.
     *
     * @param who the player to teleport
     * @param to  the destination location
     */
    void teleportSilently(@NotNull Player who, @NotNull Location to);

    /**
     * Silently teleports the player to another player's location.
     *
     * @param who    the player to teleport
     * @param target the destination player
     */
    void teleportSilently(@NotNull Player who, @NotNull Player target);
}
