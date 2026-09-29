package fr.elias.oreoEssentials.integrations.internal.api;

import fr.elias.oreoEssentials.afk.internal.api.AfkAPIImpl;
import fr.elias.oreoEssentials.commandcontrol.internal.api.AliasesAPIImpl;
import fr.elias.oreoEssentials.inventory.internal.api.AuctionHouseAPIImpl;
import fr.elias.oreoEssentials.configuration.internal.api.AutoRebootAPIImpl;
import fr.elias.oreoEssentials.player.internal.api.BackAPIImpl;
import fr.elias.oreoEssentials.bossbar.internal.api.BossBarAPIImpl;
import fr.elias.oreoEssentials.chat.internal.api.ChatAPIImpl;
import fr.elias.oreoEssentials.clearlag.internal.api.ClearLagAPIImpl;
import fr.elias.oreoEssentials.commandcontrol.internal.api.CommandControlAPIImpl;
import fr.elias.oreoEssentials.commandtoggle.internal.api.CommandToggleAPIImpl;
import fr.elias.oreoEssentials.messaging.internal.api.CrossServerAPIImpl;
import fr.elias.oreoEssentials.currency.internal.api.CurrencyAPIImpl;
import fr.elias.oreoEssentials.crafting.internal.api.CustomCraftAPIImpl;
import fr.elias.oreoEssentials.daily.internal.api.DailyAPIImpl;
import fr.elias.oreoEssentials.player.internal.api.DeathBackAPIImpl;
import fr.elias.oreoEssentials.economy.internal.api.EconomyAPIImpl;
import fr.elias.oreoEssentials.enderchest.internal.api.EnderChestAPIImpl;
import fr.elias.oreoEssentials.freeze.internal.api.FreezeAPIImpl;
import fr.elias.oreoEssentials.holograms.internal.api.HologramsAPIImpl;
import fr.elias.oreoEssentials.ignore.internal.api.IgnoreAPIImpl;
import fr.elias.oreoEssentials.commands.internal.api.InteractiveCommandsAPIImpl;
import fr.elias.oreoEssentials.jails.internal.api.JailAPIImpl;
import fr.elias.oreoEssentials.jumppads.internal.api.JumpPadsAPIImpl;
import fr.elias.oreoEssentials.kits.internal.api.KitsAPIImpl;
import fr.elias.oreoEssentials.mail.internal.api.MailAPIImpl;
import fr.elias.oreoEssentials.maintenance.internal.api.MaintenanceAPIImpl;
import fr.elias.oreoEssentials.nametag.internal.api.NametagAPIImpl;
import fr.elias.oreoEssentials.inventory.internal.api.OrdersAPIImpl;
import fr.elias.oreoEssentials.playervaults.internal.api.PlayerVaultsAPIImpl;
import fr.elias.oreoEssentials.storage.internal.api.PlayerWarpsAPIImpl;
import fr.elias.oreoEssentials.playtime.internal.api.PlaytimeAPIImpl;
import fr.elias.oreoEssentials.portals.internal.api.PortalsAPIImpl;
import fr.elias.oreoEssentials.punishment.internal.api.PunishmentAPIImpl;
import fr.elias.oreoEssentials.scoreboard.internal.api.ScoreboardAPIImpl;
import fr.elias.oreoEssentials.inventory.internal.api.SellGuiAPIImpl;
import fr.elias.oreoEssentials.shards.internal.api.ShardsAPIImpl;
import fr.elias.oreoEssentials.shop.internal.api.ShopAPIImpl;
import fr.elias.oreoEssentials.storage.internal.api.SpawnAPIImpl;
import fr.elias.oreoEssentials.player.internal.api.TeleportAPIImpl;
import fr.elias.oreoEssentials.configuration.internal.api.TempFlyAPIImpl;
import fr.elias.oreoEssentials.trade.internal.api.TradeAPIImpl;
import fr.elias.oreoEssentials.player.internal.api.VanishAPIImpl;
import fr.elias.oreoEssentials.warnings.internal.api.WarningsAPIImpl;
import fr.elias.oreoEssentials.storage.internal.api.WarpsAPIImpl;

import fr.elias.oreoEssentials.OreoEssentials;
import fr.elias.oreoEssentials.api.IAfkAPI;
import fr.elias.oreoEssentials.api.IAliasesAPI;
import fr.elias.oreoEssentials.api.IAuctionHouseAPI;
import fr.elias.oreoEssentials.api.IAutoRebootAPI;
import fr.elias.oreoEssentials.api.IBackAPI;
import fr.elias.oreoEssentials.api.IBossBarAPI;
import fr.elias.oreoEssentials.api.IChatAPI;
import fr.elias.oreoEssentials.api.IClearLagAPI;
import fr.elias.oreoEssentials.api.ICommandControlAPI;
import fr.elias.oreoEssentials.api.ICommandToggleAPI;
import fr.elias.oreoEssentials.api.ICrossServerAPI;
import fr.elias.oreoEssentials.api.ICurrencyAPI;
import fr.elias.oreoEssentials.api.ICustomCraftAPI;
import fr.elias.oreoEssentials.api.IDailyAPI;
import fr.elias.oreoEssentials.api.IDeathBackAPI;
import fr.elias.oreoEssentials.api.IEconomyAPI;
import fr.elias.oreoEssentials.api.IEnderChestAPI;
import fr.elias.oreoEssentials.api.IFreezeAPI;
import fr.elias.oreoEssentials.api.IHologramsAPI;
import fr.elias.oreoEssentials.api.IIgnoreAPI;
import fr.elias.oreoEssentials.api.IInteractiveCommandsAPI;
import fr.elias.oreoEssentials.api.IJailAPI;
import fr.elias.oreoEssentials.api.IJumpPadsAPI;
import fr.elias.oreoEssentials.api.IKitsAPI;
import fr.elias.oreoEssentials.api.IMailAPI;
import fr.elias.oreoEssentials.api.IMaintenanceAPI;
import fr.elias.oreoEssentials.api.INametagAPI;
import fr.elias.oreoEssentials.api.IOrdersAPI;
import fr.elias.oreoEssentials.api.IPlayerVaultsAPI;
import fr.elias.oreoEssentials.api.IPlayerWarpsAPI;
import fr.elias.oreoEssentials.api.IPlaytimeAPI;
import fr.elias.oreoEssentials.api.IPortalsAPI;
import fr.elias.oreoEssentials.api.IPunishmentAPI;
import fr.elias.oreoEssentials.api.IScoreboardAPI;
import fr.elias.oreoEssentials.api.ISellGuiAPI;
import fr.elias.oreoEssentials.api.IShardsAPI;
import fr.elias.oreoEssentials.api.IShopAPI;
import fr.elias.oreoEssentials.api.ISpawnAPI;
import fr.elias.oreoEssentials.api.ITeleportAPI;
import fr.elias.oreoEssentials.api.ITempFlyAPI;
import fr.elias.oreoEssentials.api.ITradeAPI;
import fr.elias.oreoEssentials.api.IVanishAPI;
import fr.elias.oreoEssentials.api.IWarningsAPI;
import fr.elias.oreoEssentials.api.IWarpsAPI;
import fr.elias.oreoEssentials.api.OreoEssentialsAPI;
import fr.elias.oreoEssentials.currency.internal.Currency;
import fr.elias.oreoEssentials.currency.internal.CurrencyService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Internal implementation of {@link OreoEssentialsAPI}. Not part of the public API surface.
 *
 * <p>All module getters are lazy — they read from the plugin instance at call time,
 * so it is safe to register this before all modules have finished initialising.
 */
public class OreoEssentialsAPIImpl implements OreoEssentialsAPI {

    private static final String API_VERSION = "1.0";

    private final OreoEssentials plugin;

    public OreoEssentialsAPIImpl(OreoEssentials plugin) {
        this.plugin = plugin;
    }

    // -------------------------------------------------------------------------
    // Meta
    // -------------------------------------------------------------------------

    @Override
    public @NotNull String version() {
        return API_VERSION;
    }

    // -------------------------------------------------------------------------
    // Currency
    // -------------------------------------------------------------------------

    @Override
    public @Nullable ICurrencyAPI currency(@NotNull String currencyId) {
        CurrencyService svc = plugin.getCurrencyService();
        if (svc == null) return null;
        Currency c = svc.getCurrency(currencyId.toLowerCase(Locale.ROOT).trim());
        if (c == null) return null;
        return new CurrencyAPIImpl(svc, c);
    }

    @Override
    public @NotNull List<ICurrencyAPI> currencies() {
        CurrencyService svc = plugin.getCurrencyService();
        if (svc == null) return List.of();
        return svc.getAllCurrencies().stream()
                .map(c -> (ICurrencyAPI) new CurrencyAPIImpl(svc, c))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasCurrency(@NotNull String currencyId) {
        CurrencyService svc = plugin.getCurrencyService();
        if (svc == null) return false;
        return svc.getCurrency(currencyId.toLowerCase(Locale.ROOT).trim()) != null;
    }

    // -------------------------------------------------------------------------
    // Modules
    // -------------------------------------------------------------------------

    @Override
    public @Nullable IOrdersAPI orders() {
        var module = plugin.getOrdersModule();
        if (module == null) return null;
        var svc = module.getService();
        if (svc == null) return null;
        return new OrdersAPIImpl(svc);
    }

    @Override
    public @Nullable INametagAPI nametag() {
        var mgr = plugin.getNametagManager();
        if (mgr == null) return null;
        return new NametagAPIImpl(mgr);
    }

    @Override
    public @Nullable IChatAPI chat() {
        var mgr = plugin.getChatSyncManager();
        if (mgr == null) return null;
        return new ChatAPIImpl(mgr);
    }

    @Override
    public @Nullable IAuctionHouseAPI auctionHouse() {
        var module = plugin.getAuctionHouseModule();
        if (module == null) return null;
        return new AuctionHouseAPIImpl(module);
    }

    @Override
    public @Nullable ITradeAPI trade() {
        var svc = plugin.getTradeService();
        if (svc == null) return null;
        return new TradeAPIImpl(svc);
    }

    @Override
    public @Nullable IShopAPI shop() {
        var module = plugin.getShopModule();
        if (module == null) return null;
        return new ShopAPIImpl(module);
    }

    @Override public @Nullable IAfkAPI afk() { var s = plugin.getAfkService(); return s == null ? null : new AfkAPIImpl(s); }
    @Override public @Nullable IBossBarAPI bossBar() { var s = plugin.getBossBarService(); return s == null ? null : new BossBarAPIImpl(s); }
    @Override public @Nullable IClearLagAPI clearLag() { var s = plugin.getClearLagManager(); return s == null ? null : new ClearLagAPIImpl(s); }
    @Override public @Nullable ICommandControlAPI commandControl() { var s = plugin.getCommandControlService(); return s == null ? null : new CommandControlAPIImpl(s); }
    @Override public @Nullable ICrossServerAPI crossServer() { var s = plugin.getModBridge(); return s == null ? null : new CrossServerAPIImpl(s); }
    @Override public @Nullable ICustomCraftAPI customCraft() { var s = plugin.getCustomCraftingService(); return s == null ? null : new CustomCraftAPIImpl(s); }
    @Override public @Nullable IDailyAPI daily() { var s = plugin.getDailyService(); return s == null ? null : new DailyAPIImpl(s); }
    @Override public @Nullable IEconomyAPI economy() {
        var eco = plugin.getEconomy();
        if (eco == null) return null;
        var svc = eco.api();
        return svc == null ? null : new EconomyAPIImpl(svc);
    }
    @Override public @Nullable IEnderChestAPI enderChest() { var s = plugin.getEnderChestService(); return s == null ? null : new EnderChestAPIImpl(s); }
    @Override public @Nullable IHologramsAPI holograms() {
        var mgr = fr.elias.oreoEssentials.holograms.internal.OHolograms.canGet()
                ? fr.elias.oreoEssentials.holograms.internal.OHolograms.get() : null;
        if (mgr == null) return null;
        var holMgr = mgr.getHologramManager();
        return holMgr == null ? null : new HologramsAPIImpl(holMgr);
    }
    @Override public @Nullable IKitsAPI kits() { var s = plugin.getKitsManager(); return s == null ? null : new KitsAPIImpl(s); }
    @Override public @Nullable IJumpPadsAPI jumpPads() { var s = plugin.getJumpPadsManager(); return s == null ? null : new JumpPadsAPIImpl(s); }
    @Override public @Nullable IJailAPI jail() { var s = plugin.getJailService(); return s == null ? null : new JailAPIImpl(s); }
    @Override public @Nullable IMaintenanceAPI maintenance() { var s = plugin.getMaintenanceService(); return s == null ? null : new MaintenanceAPIImpl(s); }
    @Override public @Nullable ISpawnAPI spawn() { var s = plugin.getSpawnService(); return s == null ? null : new SpawnAPIImpl(s); }
    @Override public @Nullable ITeleportAPI teleport() { var s = plugin.getTeleportService(); return s == null ? null : new TeleportAPIImpl(s); }
    @Override public @Nullable IWarpsAPI warps() { var s = plugin.getWarpService(); return s == null ? null : new WarpsAPIImpl(s); }
    @Override public @Nullable ISellGuiAPI sellGui() { var s = plugin.getSellGuiManager(); return s == null ? null : new SellGuiAPIImpl(s); }
    @Override public @Nullable IPunishmentAPI punishment() { var s = plugin.getPunishmentLogger(); return s == null ? null : new PunishmentAPIImpl(s); }
    @Override public @Nullable IPortalsAPI portals() { var s = plugin.getPortalsManager(); return s == null ? null : new PortalsAPIImpl(s); }
    @Override public @Nullable IPlayerWarpsAPI playerWarps() { var s = plugin.getPlayerWarpService(); return s == null ? null : new PlayerWarpsAPIImpl(s); }
    @Override public @Nullable IPlayerVaultsAPI playerVaults() { var s = plugin.getPlayervaultsService(); return s == null ? null : new PlayerVaultsAPIImpl(s); }
    @Override public @Nullable ICommandToggleAPI commandToggle() { var s = plugin.getCommandToggleService(); return s == null ? null : new CommandToggleAPIImpl(s); }
    @Override public @Nullable IInteractiveCommandsAPI interactiveCommands() { var s = plugin.getIcManager(); return s == null ? null : new InteractiveCommandsAPIImpl(s); }
    @Override public @Nullable IBackAPI back() { var s = plugin.getBackService(); return s == null ? null : new BackAPIImpl(s); }
    @Override public @Nullable IDeathBackAPI deathBack() { var s = plugin.getDeathBackService(); return s == null ? null : new DeathBackAPIImpl(s); }
    @Override public @Nullable IIgnoreAPI ignore() { var s = plugin.getIgnoreService(); return s == null ? null : new IgnoreAPIImpl(s); }
    @Override public @Nullable IMailAPI mail() { var s = plugin.getMailService(); return s == null ? null : new MailAPIImpl(s); }
    @Override public @Nullable IPlaytimeAPI playtime() {
        var tracker = plugin.getPlaytimeTracker();
        var rewards = plugin.getPlaytimeRewardsService();
        return (tracker == null || rewards == null) ? null : new PlaytimeAPIImpl(tracker, rewards);
    }
    @Override public @Nullable IScoreboardAPI scoreboard() { var s = plugin.getScoreboardService(); return s == null ? null : new ScoreboardAPIImpl(s); }
    @Override public @Nullable IShardsAPI shards() { var s = plugin.getShardsModule(); return s == null ? null : new ShardsAPIImpl(s); }
    @Override public @Nullable ITempFlyAPI tempFly() { var s = plugin.getTempFlyService(); return s == null ? null : new TempFlyAPIImpl(s); }
    @Override public @Nullable IWarningsAPI warnings() { var s = plugin.getWarnService(); return s == null ? null : new WarningsAPIImpl(s); }
    @Override public @Nullable IAutoRebootAPI autoReboot() { var s = plugin.getAutoRebootService(); return s == null ? null : new AutoRebootAPIImpl(s); }
    @Override public @Nullable IAliasesAPI aliases() { var s = plugin.getAliasService(); return s == null ? null : new AliasesAPIImpl(s); }
    @Override public @Nullable IVanishAPI vanish() { var s = plugin.getVanishService(); return s == null ? null : new VanishAPIImpl(s); }
    @Override public @Nullable IFreezeAPI freeze() { var s = plugin.getFreezeService(); return s == null ? null : new FreezeAPIImpl(s); }
}
