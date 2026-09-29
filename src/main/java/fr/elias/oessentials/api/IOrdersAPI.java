package fr.elias.oessentials.api;

import fr.elias.oessentials.inventory.internal.orders.model.FillResult;
import fr.elias.oessentials.inventory.internal.orders.model.Order;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * API for the Orders / Market module.
 *
 * <p>Obtain via {@link OEssentialsAPI#orders()}. Returns {@code null} if the
 * orders module is disabled.
 *
 * <h2>Example — create an order programmatically</h2>
 * <pre>{@code
 * IOrdersAPI orders = OEssentialsAPI.get().orders();
 * if (orders == null) return;
 *
 * orders.createOrder(player, new ItemStack(Material.DIAMOND), 64, "money", 10.0)
 *       .thenAccept(orderId -> {
 *           if (orderId != null)
 *               player.sendMessage("Order created: " + orderId);
 *       });
 * }</pre>
 */
public interface IOrdersAPI {

    /**
     * Creates a new buy order.
     *
     * @param requester  the player placing the order
     * @param item       the item being requested
     * @param qty        total quantity requested
     * @param currencyId the currency to pay with (use {@code "vault"} for Vault economy)
     * @param unitPrice  price per single item
     * @return a future resolving to the new order ID, or {@code null} on failure
     */
    @NotNull
    CompletableFuture<String> createOrder(@NotNull Player requester,
                                          @NotNull ItemStack item,
                                          int qty,
                                          @NotNull String currencyId,
                                          double unitPrice);

    /**
     * Fills (partially or fully) an existing order.
     *
     * @param filler  the player supplying items
     * @param orderId the order to fill
     * @param fillQty how many items to provide
     * @return a future resolving to a {@link FillResult} describing the outcome
     */
    @NotNull
    CompletableFuture<FillResult> fillOrder(@NotNull Player filler,
                                            @NotNull String orderId,
                                            int fillQty);

    /**
     * Cancels an order. Only the original requester (or an admin) should be passed.
     *
     * @param requester the player requesting cancellation
     * @param orderId   the order to cancel
     * @return {@code true} if cancelled successfully
     */
    @NotNull
    CompletableFuture<Boolean> cancelOrder(@NotNull Player requester,
                                           @NotNull String orderId);

    /**
     * Returns a snapshot of all currently active orders.
     */
    @NotNull
    List<Order> getActiveOrders();

    /**
     * Returns all active orders placed by the given player.
     *
     * @param playerId the player's UUID
     */
    @NotNull
    List<Order> getActiveOrdersByPlayer(@NotNull UUID playerId);

    /**
     * Finds a single order by its ID.
     *
     * @param orderId the order ID
     * @return the order if found and active
     */
    @NotNull
    Optional<Order> findOrder(@NotNull String orderId);
}
