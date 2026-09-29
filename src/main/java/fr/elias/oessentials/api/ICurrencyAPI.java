package fr.elias.oessentials.api;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a single registered currency and exposes balance operations.
 *
 * <p>All balance methods are async and return {@link CompletableFuture}.
 * Do <b>not</b> block the main thread waiting on them — use
 * {@link CompletableFuture#thenAccept(java.util.function.Consumer)} or
 * schedule a follow-up task.
 *
 * <h2>Quick example</h2>
 * <pre>{@code
 * ICurrencyAPI euro = OEssentialsAPI.get().currency("euro");
 * if (euro == null) return; // currency not registered on this server
 *
 * // Charge a player
 * euro.withdraw(player.getUniqueId(), 50.0).thenAccept(success -> {
 *     if (success) {
 *         Bukkit.getScheduler().runTask(plugin, () -> rewardPlayer(player));
 *     }
 * });
 * }</pre>
 */
public interface ICurrencyAPI {

    // -------------------------------------------------------------------------
    // Metadata
    // -------------------------------------------------------------------------

    /** Unique lowercase identifier (e.g. {@code "euro"}). */
    @NotNull String getId();

    /** Display name (e.g. {@code "Euro"}). */
    @NotNull String getName();

    /** Symbol used when formatting amounts (e.g. {@code "€"}). */
    @NotNull String getSymbol();

    /** Human-readable display name (may equal {@link #getName()}). */
    @NotNull String getDisplayName();

    /** The default balance assigned to new players for this currency. */
    double getDefaultBalance();

    /** Whether players can send this currency to each other via {@code /currencysend}. */
    boolean isTradeable();

    /** Whether balances can go below zero. */
    boolean isAllowNegative();

    /**
     * Formats an amount with this currency's symbol.
     * Example: {@code format(10.5)} → {@code "€ 10.50"}
     */
    @NotNull String format(double amount);

    // -------------------------------------------------------------------------
    // Balance operations (all async)
    // -------------------------------------------------------------------------

    /**
     * Gets the current balance of a player for this currency.
     *
     * @param playerId the player's UUID (online or offline)
     * @return a future resolving to the balance (≥ 0 unless {@link #isAllowNegative()})
     */
    @NotNull CompletableFuture<Double> getBalance(@NotNull UUID playerId);

    /**
     * Returns {@code true} if the player has at least {@code amount} of this currency.
     *
     * @param playerId the player's UUID
     * @param amount   amount to check (must be > 0)
     */
    @NotNull CompletableFuture<Boolean> has(@NotNull UUID playerId, double amount);

    /**
     * Adds {@code amount} to the player's balance.
     * Fires a cancellable {@link fr.elias.oessentials.api.events.CurrencyTransactionEvent}
     * before executing.
     *
     * @param playerId the player's UUID
     * @param amount   amount to add (must be > 0)
     * @return {@code true} on success, {@code false} if cancelled or invalid
     */
    @NotNull CompletableFuture<Boolean> deposit(@NotNull UUID playerId, double amount);

    /**
     * Subtracts {@code amount} from the player's balance.
     * Fires a cancellable {@link fr.elias.oessentials.api.events.CurrencyTransactionEvent}
     * before executing.
     *
     * @param playerId the player's UUID
     * @param amount   amount to subtract (must be > 0)
     * @return {@code true} on success, {@code false} if insufficient funds, cancelled, or invalid
     */
    @NotNull CompletableFuture<Boolean> withdraw(@NotNull UUID playerId, double amount);

    /**
     * Sets the player's balance to an exact value.
     * Fires a cancellable {@link fr.elias.oessentials.api.events.CurrencyTransactionEvent}
     * before executing.
     *
     * @param playerId the player's UUID
     * @param amount   the new balance
     * @return a future that completes when the write is done
     */
    @NotNull CompletableFuture<Void> setBalance(@NotNull UUID playerId, double amount);

    /**
     * Transfers {@code amount} from one player to another.
     * Requires this currency to be {@link #isTradeable()}.
     * Fires a cancellable {@link fr.elias.oessentials.api.events.CurrencyTransferEvent}
     * before executing.
     *
     * @param from   sender UUID
     * @param to     recipient UUID
     * @param amount amount to transfer (must be > 0)
     * @return {@code true} on success
     */
    @NotNull CompletableFuture<Boolean> transfer(@NotNull UUID from, @NotNull UUID to, double amount);
}
