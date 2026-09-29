package fr.elias.oreoEssentials.inventory.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.AuctionHouseModule;
import fr.elias.oreoEssentials.inventory.internal.sellgui.manager.SellGuiManager;
import fr.minuskube.inv.InventoryManager;

@ModuleApi("services")
public interface InventoriesServices {
    AuctionHouseModule getAuctionHouse();
    fr.elias.oreoEssentials.inventory.internal.orders.OrdersModule getOrdersModule();
    InventoryManager getInvManager();
    SellGuiManager getSellGuiManager();
}
