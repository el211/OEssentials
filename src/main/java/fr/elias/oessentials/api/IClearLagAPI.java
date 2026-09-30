package fr.elias.oessentials.api;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * API for the ClearLag module.
 * Obtain via {@link OEssentialsAPI#clearLag()}. Returns {@code null} if disabled.
 */
public interface IClearLagAPI {

    /**
     * Clears dropped items on the ground.
     *
     * @param sender the command sender to receive feedback messages, or {@code null} for silent
     * @return the number of entities removed
     */
    int clearItems(@NotNull CommandSender sender);

    /**
     * Kills hostile mobs.
     *
     * @param sender the command sender to receive feedback messages, or {@code null} for silent
     * @return the number of mobs killed
     */
    int killMobs(@NotNull CommandSender sender);

    /** Reloads the ClearLag configuration. */
    void reload();
}
