package fr.elias.oreoEssentials.shop.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.shop.internal.ShopModule;

@ModuleApi("services")
public interface ShopServices {
    ShopModule getShopModule();
}
