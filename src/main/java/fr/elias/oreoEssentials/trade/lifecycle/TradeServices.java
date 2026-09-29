package fr.elias.oreoEssentials.trade.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.trade.internal.config.TradeConfig;
import fr.elias.oreoEssentials.trade.internal.service.TradeService;

@ModuleApi("services")
public interface TradeServices {
    TradeConfig getTradeConfig();
    TradeService getTradeService();
}
