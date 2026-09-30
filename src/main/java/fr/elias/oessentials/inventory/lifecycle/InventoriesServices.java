package fr.elias.oessentials.inventory.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.inventory.internal.auctionhouse.AuctionHouseModule;
import fr.elias.oessentials.inventory.internal.sellgui.manager.SellGuiManager;
import fr.minuskube.inv.InventoryManager;

@ModuleApi("services")
public interface InventoriesServices {
    AuctionHouseModule getAuctionHouse();
    fr.elias.oessentials.inventory.internal.orders.OrdersModule getOrdersModule();
    InventoryManager getInvManager();
    SellGuiManager getSellGuiManager();
}
