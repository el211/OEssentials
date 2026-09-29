package fr.elias.oreoEssentials.shop.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "shop", dependencies = {"clear-lag", "configuration::services"})
public final class ShopModule extends ManagedModule implements ShopServices {
    private fr.elias.oreoEssentials.shop.internal.ShopModule shopModule;

    @Override
    protected void start() {
        cleanup("shopModule", () -> { if (shopModule != null) shopModule.shutdown(); });
        initShop();
    }

    @Override public fr.elias.oreoEssentials.shop.internal.ShopModule getShopModule() { return shopModule; }

    private void initShop() {
        if (!services(ConfigurationServices.class).getSettingsConfig().getRoot().getBoolean("shop.enabled", true)) {
            plugin.getLogger().info("[Shop] Disabled by settings.yml");
            return;
        }
        try {
            this.shopModule = new fr.elias.oreoEssentials.shop.internal.ShopModule(plugin);
            if (shopModule.isEnabled()) {
                plugin.getLogger().info("[Shop] Module ready.");
            }
        } catch (Throwable t) {
            plugin.getLogger().severe("[Shop] Failed to initialise: " + t.getMessage());
            t.printStackTrace();
            this.shopModule = null;
        }
    }
}
