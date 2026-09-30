package fr.elias.oessentials.api;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for the built-in Vault-compatible economy (single-currency, OES-managed).
 *
 * <p>This wraps OES's own economy implementation that registers with Vault.
 * For the multi-currency system use {@link ICurrencyAPI} instead.
 *
 * <p>Obtain via {@link OEssentialsAPI#economy()}. Returns {@code null} if the
 * economy module is disabled.
 *
 * <p>All methods are synchronous because the underlying {@code EconomyService}
 * operates on an in-memory cache that is already loaded on the server thread.
 */
public interface IEconomyAPI {

    /**
     * Returns the current balance of the player.
     *
     * @param playerId the player's UUID
     */
    double getBalance(@NotNull UUID playerId);

    /**
     * Adds {@code amount} to the player's balance.
     *
     * @param playerId the player's UUID
     * @param amount   amount to add (must be &gt; 0)
     * @return {@code true} on success
     */
    boolean deposit(@NotNull UUID playerId, double amount);

    /**
     * Subtracts {@code amount} from the player's balance.
     *
     * @param playerId the player's UUID
     * @param amount   amount to subtract (must be &gt; 0)
     * @return {@code false} if insufficient funds
     */
    boolean withdraw(@NotNull UUID playerId, double amount);

    /**
     * Transfers {@code amount} from one player to another.
     *
     * @param from   sender UUID
     * @param to     recipient UUID
     * @param amount amount to transfer
     * @return {@code false} if sender has insufficient funds
     */
    boolean transfer(@NotNull UUID from, @NotNull UUID to, double amount);
}
