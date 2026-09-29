package fr.elias.oessentials.shop.internal.api;

import fr.elias.oessentials.api.IShopAPI;
import fr.elias.oessentials.shop.internal.ShopModule;
import fr.elias.oessentials.shop.internal.models.Shop;
import fr.elias.oessentials.shop.internal.models.ShopItem;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ShopAPIImpl implements IShopAPI {

    private final ShopModule module;

    public ShopAPIImpl(ShopModule module) {
        this.module = module;
    }

    @Override
    public boolean isEnabled() {
        return module.isEnabled();
    }

    @Override
    public @NotNull Map<String, Shop> getAllShops() {
        return module.getShopManager().getAllShops();
    }

    @Override
    public @Nullable Shop getShop(@NotNull String shopId) {
        return module.getShopManager().getShop(shopId);
    }

    @Override
    public boolean processBuy(@NotNull Player player, @NotNull ShopItem shopItem, int quantity) {
        return module.getTransactionProcessor().processBuy(player, shopItem, quantity);
    }

    @Override
    public boolean processSell(@NotNull Player player, @NotNull ShopItem shopItem, int quantity) {
        return module.getTransactionProcessor().processSell(player, shopItem, quantity);
    }
}
