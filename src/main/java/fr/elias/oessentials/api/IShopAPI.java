package fr.elias.oessentials.api;

import fr.elias.oessentials.shop.internal.models.Shop;
import fr.elias.oessentials.shop.internal.models.ShopItem;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * API for the Shop module.
 *
 * <p>Obtain via {@link OEssentialsAPI#shop()}. Returns {@code null} if the
 * shop module is disabled.
 *
 * <h2>Example — programmatic buy</h2>
 * <pre>{@code
 * IShopAPI shop = OEssentialsAPI.get().shop();
 * if (shop == null || !shop.isEnabled()) return;
 *
 * Shop weapons = shop.getShop("weapons");
 * if (weapons != null) {
 *     ShopItem sword = weapons.getItems().stream()
 *         .filter(i -> i.getMaterial() == Material.DIAMOND_SWORD)
 *         .findFirst().orElse(null);
 *     if (sword != null) shop.processBuy(player, sword, 1);
 * }
 * }</pre>
 */
public interface IShopAPI {

    /**
     * Returns {@code true} if the shop module is active.
     */
    boolean isEnabled();

    /**
     * Returns an unmodifiable map of all registered shops, keyed by their ID.
     */
    @NotNull
    Map<String, Shop> getAllShops();

    /**
     * Returns the {@link Shop} with the given ID, or {@code null} if none exists.
     *
     * @param shopId the shop identifier (case-insensitive)
     */
    @Nullable
    Shop getShop(@NotNull String shopId);

    /**
     * Processes a buy transaction for the given player and shop item.
     * Handles economy deduction, inventory insertion, and logging.
     *
     * @param player   the buying player
     * @param shopItem the item being bought
     * @param quantity how many to buy
     * @return {@code true} if the transaction succeeded
     */
    boolean processBuy(@NotNull Player player,
                       @NotNull ShopItem shopItem,
                       int quantity);

    /**
     * Processes a sell transaction for the given player and shop item.
     * Handles inventory removal, economy deposit, and logging.
     *
     * @param player   the selling player
     * @param shopItem the item being sold
     * @param quantity how many to sell
     * @return {@code true} if the transaction succeeded
     */
    boolean processSell(@NotNull Player player,
                        @NotNull ShopItem shopItem,
                        int quantity);
}
