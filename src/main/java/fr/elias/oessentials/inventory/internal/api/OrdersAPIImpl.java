package fr.elias.oessentials.inventory.internal.api;

import fr.elias.oessentials.api.IOrdersAPI;
import fr.elias.oessentials.inventory.internal.orders.model.FillResult;
import fr.elias.oessentials.inventory.internal.orders.model.Order;
import fr.elias.oessentials.inventory.internal.orders.service.OrderService;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class OrdersAPIImpl implements IOrdersAPI {

    private final OrderService service;

    public OrdersAPIImpl(OrderService service) {
        this.service = service;
    }

    @Override
    public @NotNull CompletableFuture<String> createOrder(@NotNull Player requester,
                                                          @NotNull ItemStack item,
                                                          int qty,
                                                          @NotNull String currencyId,
                                                          double unitPrice) {
        return service.createOrder(requester, item, qty, currencyId, unitPrice);
    }

    @Override
    public @NotNull CompletableFuture<FillResult> fillOrder(@NotNull Player filler,
                                                            @NotNull String orderId,
                                                            int fillQty) {
        return service.fillOrder(filler, orderId, fillQty);
    }

    @Override
    public @NotNull CompletableFuture<Boolean> cancelOrder(@NotNull Player requester,
                                                           @NotNull String orderId) {
        return service.cancelOrder(requester, orderId);
    }

    @Override
    public @NotNull List<Order> getActiveOrders() {
        return service.getActiveOrders();
    }

    @Override
    public @NotNull List<Order> getActiveOrdersByPlayer(@NotNull UUID playerId) {
        return service.getActiveOrdersByPlayer(playerId);
    }

    @Override
    public @NotNull Optional<Order> findOrder(@NotNull String orderId) {
        return service.findOrder(orderId);
    }
}
