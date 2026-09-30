package fr.elias.oessentials.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * API for the CommandControl module (command blocking/restriction).
 * Obtain via {@link OEssentialsAPI#commandControl()}. Returns {@code null} if disabled.
 */
public interface ICommandControlAPI {

    /** Returns {@code true} if command control is enabled. */
    boolean isEnabled();

    /**
     * Returns {@code true} if the player has bypass permission and is exempt from all restrictions.
     */
    boolean canBypass(@NotNull Player player);

    /**
     * Returns {@code true} if the command (root + sub) is blocked for this player.
     *
     * @param player  the player executing the command
     * @param root    the root command (e.g. {@code "tp"})
     * @param sub     the sub-command (e.g. {@code "other"}) or empty string
     * @param rawLine the full raw command line
     */
    boolean isBlocked(@NotNull Player player,
                      @NotNull String root,
                      @NotNull String sub,
                      @NotNull String rawLine);

    /**
     * Returns {@code true} if the player can use the given sub-command of a root command.
     */
    boolean canUseSub(@NotNull Player player, @NotNull String root, @NotNull String sub);
}
