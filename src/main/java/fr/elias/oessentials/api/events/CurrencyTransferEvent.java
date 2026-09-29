package fr.elias.oessentials.api.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired before a player-to-player currency transfer is applied.
 *
 * <p>This event is <b>asynchronous</b>. Do not call synchronous Bukkit API
 * from your listener without scheduling onto the main thread.
 *
 * <p>Cancel the event to block the transfer entirely. Both sender and
 * recipient balances will be left unchanged.
 *
 * <h2>Example</h2>
 * <pre>{@code
 * @EventHandler
 * public void onTransfer(CurrencyTransferEvent e) {
 *     if (e.getCurrencyId().equals("euro") && e.getAmount() > 500) {
 *         e.setCancelled(true);
 *     }
 * }
 * }</pre>
 */
public class CurrencyTransferEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final UUID from;
    private final UUID to;
    private final String currencyId;
    private final double amount;
    private boolean cancelled = false;

    /**
     * @param from       the UUID of the player sending currency
     * @param to         the UUID of the player receiving currency
     * @param currencyId the currency being transferred
     * @param amount     the amount being transferred
     */
    public CurrencyTransferEvent(@NotNull UUID from,
                                 @NotNull UUID to,
                                 @NotNull String currencyId,
                                 double amount) {
        super(true); // async = true
        this.from = from;
        this.to = to;
        this.currencyId = currencyId;
        this.amount = amount;
    }

    /** The UUID of the player sending currency. */
    @NotNull
    public UUID getFrom() {
        return from;
    }

    /** The UUID of the player receiving currency. */
    @NotNull
    public UUID getTo() {
        return to;
    }

    /** The ID of the currency being transferred (lowercase). */
    @NotNull
    public String getCurrencyId() {
        return currencyId;
    }

    /** The amount being transferred. */
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
