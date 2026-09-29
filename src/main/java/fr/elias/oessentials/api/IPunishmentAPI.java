package fr.elias.oessentials.api;

import fr.elias.oessentials.punishment.internal.PunishmentLogger;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * API for the Punishment logger module.
 * Obtain via {@link OEssentialsAPI#punishment()}. Returns {@code null} if disabled.
 */
public interface IPunishmentAPI {

    /**
     * Returns the full punishment history for the given player.
     *
     * @param playerId the player's UUID
     */
    @NotNull List<PunishmentLogger.PunishEntry> getHistory(@NotNull UUID playerId);

    /**
     * Clears the punishment history for the given player.
     *
     * @param playerId the player's UUID
     */
    void clearHistory(@NotNull UUID playerId);
}
