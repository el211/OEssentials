package fr.elias.oessentials.shop.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.shop.internal.ShopModule;

@ModuleApi("services")
public interface ShopServices {
    ShopModule getShopModule();
}
