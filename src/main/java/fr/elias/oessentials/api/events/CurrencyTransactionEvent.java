package fr.elias.oessentials.api.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired before a currency transaction (deposit, withdraw, or set) is applied.
 *
 * <p>This event is <b>asynchronous</b> — do not call synchronous Bukkit API
 * from your listener. Use {@link org.bukkit.scheduler.BukkitScheduler#runTask}
 * if you need to execute something on the main thread.
 *
 * <p>Cancel the event to prevent the transaction from being applied.
 *
 * <h2>Example</h2>
 * <pre>{@code
 * @EventHandler
 * public void onWithdraw(CurrencyTransactionEvent e) {
 *     if (e.getType() == CurrencyTransactionEvent.Type.WITHDRAW
 *             && e.getCurrencyId().equals("euro")
 *             && e.getAmount() > 1000) {
 *         e.setCancelled(true);
 *         // notify player on main thread:
 *         Bukkit.getScheduler().runTask(plugin, () ->
 *             Bukkit.getPlayer(e.getPlayerId()).sendMessage("Transfer limit exceeded!"));
 *     }
 * }
 * }</pre>
 */
public class CurrencyTransactionEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    /** The type of transaction being performed. */
    public enum Type {
        DEPOSIT,
        WITHDRAW,
        SET
    }

    private final UUID playerId;
    private final String currencyId;
    private final Type type;
    private final double amount;
    private boolean cancelled = false;

    /**
     * @param playerId   the player whose balance is being modified
     * @param currencyId the currency being affected
     * @param type       the type of transaction
     * @param amount     the amount involved
     */
    public CurrencyTransactionEvent(@NotNull UUID playerId,
                                    @NotNull String currencyId,
                                    @NotNull Type type,
                                    double amount) {
        super(true); // async = true
        this.playerId = playerId;
        this.currencyId = currencyId;
        this.type = type;
        this.amount = amount;
    }

    /** The UUID of the player whose balance is being modified. */
    @NotNull
    public UUID getPlayerId() {
        return playerId;
    }

    /** The ID of the currency being modified (lowercase, e.g. {@code "euro"}). */
    @NotNull
    public String getCurrencyId() {
        return currencyId;
    }

    /** Whether this is a deposit, withdraw, or set operation. */
    @NotNull
    public Type getType() {
        return type;
    }

    /** The amount being deposited, withdrawn, or set. */
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
