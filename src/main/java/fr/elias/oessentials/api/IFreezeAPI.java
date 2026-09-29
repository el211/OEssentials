package fr.elias.oessentials.api;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for the Freeze module — immobilising players.
 *
 * <p>Obtain via {@link OEssentialsAPI#freeze()}. Returns {@code null} if disabled.
 */
public interface IFreezeAPI {

    /**
     * Returns {@code true} if the player is currently frozen.
     *
     * @param playerId the player's UUID
     */
    boolean isFrozen(@NotNull UUID playerId);

    /**
     * Sets the frozen state for the player.
     *
     * @param playerId the player's UUID
     * @param frozen   {@code true} to freeze, {@code false} to unfreeze
     */
    void setFrozen(@NotNull UUID playerId, boolean frozen);
}
