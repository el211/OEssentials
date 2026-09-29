package fr.elias.oreoEssentials.trade.internal.api;

import fr.elias.oreoEssentials.api.ITradeAPI;
import fr.elias.oreoEssentials.trade.internal.TradeSession;
import fr.elias.oreoEssentials.trade.internal.service.TradeService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TradeAPIImpl implements ITradeAPI {

    private final TradeService service;

    public TradeAPIImpl(TradeService service) {
        this.service = service;
    }

    @Override
    public void sendInvite(@NotNull Player requester, @NotNull Player target) {
        service.sendInvite(requester, target);
    }

    @Override
    public boolean isInTrade(@NotNull UUID playerId) {
        return service.getTradeIdByPlayer(playerId) != null;
    }

    @Override
    public @Nullable UUID getTradeIdByPlayer(@NotNull UUID playerId) {
        return service.getTradeIdByPlayer(playerId);
    }

    @Override
    public @Nullable TradeSession getSession(@NotNull UUID tradeId) {
        return service.getSession(tradeId);
    }

    @Override
    public void cancelTradeFor(@NotNull UUID playerId, @NotNull String reason) {
        service.cancelTradeFor(playerId, reason);
    }
}
