package fr.elias.oessentials.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Public API entry point for OEssentials.
 *
 * <h2>Usage from an external plugin</h2>
 * <pre>{@code
 * // In onEnable:
 * RegisteredServiceProvider<OEssentialsAPI> rsp =
 *     Bukkit.getServicesManager().getRegistration(OEssentialsAPI.class);
 * if (rsp == null) {
 *     getLogger().warning("OEssentials not found — disabling bridge.");
 *     return;
 * }
 * OEssentialsAPI oes = rsp.getProvider();
 *
 * // Check a player's "euro" balance and deduct it:
 * ICurrencyAPI euro = oes.currency("euro");
 * if (euro != null) {
 *     euro.has(playerUuid, price).thenAccept(canAfford -> {
 *         if (canAfford) {
 *             euro.withdraw(playerUuid, price).thenAccept(success -> {
 *                 if (success) giveCrateToPlayer(player);
 *             });
 *         }
 *     });
 * }
 * }</pre>
 *
 * <h2>Listening for currency events</h2>
 * <pre>{@code
 * @EventHandler
 * public void onDeposit(CurrencyTransactionEvent e) {
 *     if (e.getType() == CurrencyTransactionEvent.Type.DEPOSIT
 *             && e.getCurrencyId().equals("euro")) {
 *         e.setCancelled(true); // block it
 *     }
 * }
 * }</pre>
 */
public interface OEssentialsAPI {

    /**
     * Returns the API version string (e.g. {@code "1.0"}).
     */
    @NotNull
    String version();

    /**
     * Returns the {@link ICurrencyAPI} for the given currency ID, or {@code null}
     * if no currency with that ID is registered.
     *
     * @param currencyId case-insensitive currency identifier (e.g. {@code "euro"}, {@code "money"})
     */
    @Nullable
    ICurrencyAPI currency(@NotNull String currencyId);

    /**
     * Returns a snapshot list of all registered currencies.
     */
    @NotNull
    List<ICurrencyAPI> currencies();

    /**
     * Returns {@code true} if a currency with the given ID exists.
     *
     * @param currencyId case-insensitive currency identifier
     */
    boolean hasCurrency(@NotNull String currencyId);

    // -------------------------------------------------------------------------
    // Modules  (null = module disabled on this server)
    // -------------------------------------------------------------------------

    /**
     * Access the Orders / Market module API.
     * Returns {@code null} if the orders module is disabled.
     */
    @Nullable
    IOrdersAPI orders();

    /**
     * Access the Nametag module API.
     * Returns {@code null} if the nametag module is disabled.
     */
    @Nullable
    INametagAPI nametag();

    /**
     * Access the Chat / cross-server messaging API.
     * Returns {@code null} if the chat sync manager is not running.
     */
    @Nullable
    IChatAPI chat();

    /**
     * Access the Auction House module API.
     * Returns {@code null} if the auction house is disabled.
     */
    @Nullable
    IAuctionHouseAPI auctionHouse();

    /**
     * Access the Trade module API.
     * Returns {@code null} if the trade module is disabled.
     */
    @Nullable
    ITradeAPI trade();

    /**
     * Access the Shop module API.
     * Returns {@code null} if the shop module is disabled.
     */
    @Nullable
    IShopAPI shop();

    /** AFK module. Null if disabled. */
    @Nullable IAfkAPI afk();
    /** BossBar module. Null if disabled. */
    @Nullable IBossBarAPI bossBar();
    /** ClearLag module. Null if disabled. */
    @Nullable IClearLagAPI clearLag();
    /** CommandControl module (command blocking). Null if disabled. */
    @Nullable ICommandControlAPI commandControl();
    /** Cross-server moderation bridge. Null if disabled. */
    @Nullable ICrossServerAPI crossServer();
    /** Custom Crafting module. Null if disabled. */
    @Nullable ICustomCraftAPI customCraft();
    /** Daily Rewards module. Null if disabled. */
    @Nullable IDailyAPI daily();
    /** Built-in Vault economy. Null if disabled. */
    @Nullable IEconomyAPI economy();
    /** EnderChest module. Null if disabled. */
    @Nullable IEnderChestAPI enderChest();
    /** Holograms module (OHolograms). Null if disabled. */
    @Nullable IHologramsAPI holograms();
    /** Kits module. Null if disabled. */
    @Nullable IKitsAPI kits();
    /** JumpPads module. Null if disabled. */
    @Nullable IJumpPadsAPI jumpPads();
    /** Jail module. Null if disabled. */
    @Nullable IJailAPI jail();
    /** Maintenance module. Null if disabled. */
    @Nullable IMaintenanceAPI maintenance();
    /** Spawn module. Null if disabled. */
    @Nullable ISpawnAPI spawn();
    /** Teleport / TPA module. Null if disabled. */
    @Nullable ITeleportAPI teleport();
    /** Warps module. Null if disabled. */
    @Nullable IWarpsAPI warps();
    /** SellGUI module. Null if disabled. */
    @Nullable ISellGuiAPI sellGui();
    /** Punishment logger module. Null if disabled. */
    @Nullable IPunishmentAPI punishment();
    /** Portals module. Null if disabled. */
    @Nullable IPortalsAPI portals();
    /** Player Warps module. Null if disabled. */
    @Nullable IPlayerWarpsAPI playerWarps();
    /** Player Vaults module. Null if disabled. */
    @Nullable IPlayerVaultsAPI playerVaults();
    /** CommandToggle module (runtime command enable/disable). Null if disabled. */
    @Nullable ICommandToggleAPI commandToggle();
    /** Interactive Commands (IC) module. Null if disabled. */
    @Nullable IInteractiveCommandsAPI interactiveCommands();
    /** /back last-location tracking. Null if disabled. */
    @Nullable IBackAPI back();
    /** Death-back location tracking. Null if disabled. */
    @Nullable IDeathBackAPI deathBack();
    /** Player ignore lists. Null if disabled. */
    @Nullable IIgnoreAPI ignore();
    /** In-game mail. Null if disabled. */
    @Nullable IMailAPI mail();
    /** Playtime tracking and rewards. Null if disabled. */
    @Nullable IPlaytimeAPI playtime();
    /** Animated sidebar scoreboard. Null if disabled. */
    @Nullable IScoreboardAPI scoreboard();
    /** World sharding (cross-shard transfers). Null if disabled. */
    @Nullable IShardsAPI shards();
    /** TempFly (time-limited flight). Null if disabled. */
    @Nullable ITempFlyAPI tempFly();
    /** Player warnings. Null if disabled. */
    @Nullable IWarningsAPI warnings();
    /** Auto-reboot scheduler. Null if disabled. */
    @Nullable IAutoRebootAPI autoReboot();
    /** Command aliases with checks and cooldowns. Null if disabled. */
    @Nullable IAliasesAPI aliases();
    /** Vanish (staff invisibility). Null if disabled. */
    @Nullable IVanishAPI vanish();
    /** Freeze (immobilise players). Null if disabled. */
    @Nullable IFreezeAPI freeze();

    // -------------------------------------------------------------------------
    // Static accessor
    // -------------------------------------------------------------------------

    /**
     * Retrieves the registered {@link OEssentialsAPI} from Bukkit's
     * {@link org.bukkit.plugin.ServicesManager}.
     *
     * @return the API instance, or {@code null} if OEssentials is not loaded
     */
    @Nullable
    static OEssentialsAPI get() {
        RegisteredServiceProvider<OEssentialsAPI> rsp =
                Bukkit.getServicesManager().getRegistration(OEssentialsAPI.class);
        return rsp != null ? rsp.getProvider() : null;
    }
}
