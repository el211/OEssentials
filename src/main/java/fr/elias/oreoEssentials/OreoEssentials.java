package fr.elias.oreoEssentials;

import com.google.gson.Gson;
import dev.oreo.modulith.paper.PaperModulith;
import fr.elias.oreoEssentials.platform.modularity.OreoModuleRegistry;
import fr.elias.oreoEssentials.platform.modularity.OreoModules;
import fr.elias.oreoEssentials.afk.lifecycle.AfkServices;
import fr.elias.oreoEssentials.bossbar.lifecycle.BossBarServices;
import fr.elias.oreoEssentials.chat.lifecycle.ChatServices;
import fr.elias.oreoEssentials.clearlag.lifecycle.ClearLagServices;
import fr.elias.oreoEssentials.commandcontrol.lifecycle.CommandControlServices;
import fr.elias.oreoEssentials.commands.lifecycle.CommandsServices;
import fr.elias.oreoEssentials.commandtoggle.lifecycle.CommandToggleServices;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.currency.lifecycle.CurrencySystemServices;
import fr.elias.oreoEssentials.crafting.lifecycle.CustomCraftingServices;
import fr.elias.oreoEssentials.daily.lifecycle.DailyServices;
import fr.elias.oreoEssentials.dialogs.lifecycle.DialogsServices;
import fr.elias.oreoEssentials.discord.lifecycle.DiscordIntegrationServices;
import fr.elias.oreoEssentials.economy.lifecycle.EconomyServices;
import fr.elias.oreoEssentials.enderchest.lifecycle.EnderChestServices;
import fr.elias.oreoEssentials.freeze.lifecycle.FreezeServices;
import fr.elias.oreoEssentials.grouprtp.lifecycle.GroupRtpServices;
import fr.elias.oreoEssentials.ignore.lifecycle.IgnoreServices;
import fr.elias.oreoEssentials.integrations.lifecycle.IntegrationsServices;
import fr.elias.oreoEssentials.inventory.lifecycle.InventoriesServices;
import fr.elias.oreoEssentials.jails.lifecycle.JailsServices;
import fr.elias.oreoEssentials.jumppads.lifecycle.JumpPadsServices;
import fr.elias.oreoEssentials.kits.lifecycle.KitsServices;
import fr.elias.oreoEssentials.mail.lifecycle.MailServices;
import fr.elias.oreoEssentials.maintenance.lifecycle.MaintenanceServices;
import fr.elias.oreoEssentials.messaging.lifecycle.MessagingServices;
import fr.elias.oreoEssentials.mobs.lifecycle.MobsServices;
import fr.elias.oreoEssentials.modgui.lifecycle.ModGuiServices;
import fr.elias.oreoEssentials.mute.lifecycle.MuteSystemServices;
import fr.elias.oreoEssentials.nametag.lifecycle.NametagServices;
import fr.elias.oreoEssentials.notes.lifecycle.NotesServices;
import fr.elias.oreoEssentials.guards.lifecycle.PerformanceGuardsServices;
import fr.elias.oreoEssentials.player.lifecycle.PlayerServices;
import fr.elias.oreoEssentials.playervaults.lifecycle.PlayerVaultsServices;
import fr.elias.oreoEssentials.playtime.lifecycle.PlaytimeServices;
import fr.elias.oreoEssentials.portals.lifecycle.PortalsServices;
import fr.elias.oreoEssentials.proxy.lifecycle.ProxyMessagingServices;
import fr.elias.oreoEssentials.punishment.lifecycle.PunishmentLoggerServices;
import fr.elias.oreoEssentials.redis.lifecycle.RedisServices;
import fr.elias.oreoEssentials.rtp.lifecycle.RtpServices;
import fr.elias.oreoEssentials.scoreboard.lifecycle.ScoreboardServices;
import fr.elias.oreoEssentials.shards.lifecycle.ShardingServices;
import fr.elias.oreoEssentials.shop.lifecycle.ShopServices;
import fr.elias.oreoEssentials.storage.lifecycle.StorageServices;
import fr.elias.oreoEssentials.tab.lifecycle.TabServices;
import fr.elias.oreoEssentials.trade.lifecycle.TradeServices;
import fr.elias.oreoEssentials.warnings.lifecycle.WarningsServices;
import fr.elias.oreoEssentials.platform.commands.CommandManager;
import fr.elias.oreoEssentials.configuration.internal.config.ConfigService;
import fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig;
import fr.elias.oreoEssentials.economy.internal.persistence.database.PlayerEconomyDatabase;
import fr.elias.oreoEssentials.redis.internal.persistence.database.RedisManager;
import fr.elias.oreoEssentials.freeze.internal.freeze.FreezeManager;
import fr.elias.oreoEssentials.modgui.internal.ip.IpTracker;
import fr.elias.oreoEssentials.notes.internal.notes.NotesChatListener;
import fr.elias.oreoEssentials.notes.internal.notes.PlayerNotesManager;
import fr.elias.oreoEssentials.afk.internal.AfkPoolService;
import fr.elias.oreoEssentials.afk.internal.AfkService;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.AuctionHouseModule;
import fr.elias.oreoEssentials.configuration.internal.autoreboot.AutoRebootService;
import fr.elias.oreoEssentials.player.internal.back.BackLocation;
import fr.elias.oreoEssentials.player.internal.back.rabbit.BackBroker;
import fr.elias.oreoEssentials.player.internal.back.service.BackService;
import fr.elias.oreoEssentials.chat.internal.ChatSyncManager;
import fr.elias.oreoEssentials.chat.internal.chatservices.MuteService;
import fr.elias.oreoEssentials.clearlag.internal.ClearLagManager;
import fr.elias.oreoEssentials.commandtoggle.internal.CommandToggleService;
import fr.elias.oreoEssentials.messaging.internal.cross.ModBridge;
import fr.elias.oreoEssentials.currency.internal.CurrencyConfig;
import fr.elias.oreoEssentials.currency.internal.CurrencyService;
import fr.elias.oreoEssentials.currency.internal.placeholders.CurrencyPlaceholderExpansion;
import fr.elias.oreoEssentials.crafting.internal.customcraft.CraftActionsConfig;
import fr.elias.oreoEssentials.crafting.internal.customcraft.CustomCraftingService;
import fr.elias.oreoEssentials.player.internal.deathback.DeathBackService;
import fr.elias.oreoEssentials.economy.internal.EconomyBootstrap;
import fr.elias.oreoEssentials.storage.internal.homes.TeleportBroker;
import fr.elias.oreoEssentials.storage.internal.homes.home.HomeService;
import fr.elias.oreoEssentials.configuration.internal.invlook.manager.InvlookManager;
import fr.elias.oreoEssentials.messaging.internal.invsee.InvseeService;
import fr.elias.oreoEssentials.nametag.internal.PlayerNametagManager;
import fr.elias.oreoEssentials.storage.internal.playerwarp.PlayerWarpDirectory;
import fr.elias.oreoEssentials.storage.internal.playerwarp.PlayerWarpService;
import fr.elias.oreoEssentials.rtp.internal.RtpConfig;
import fr.elias.oreoEssentials.rtp.internal.RtpPendingService;
import fr.elias.oreoEssentials.scoreboard.internal.ScoreboardService;
import fr.elias.oreoEssentials.inventory.internal.sellgui.manager.SellGuiManager;
import fr.elias.oreoEssentials.shop.internal.ShopModule;
import fr.elias.oreoEssentials.storage.internal.spawn.SpawnDirectory;
import fr.elias.oreoEssentials.storage.internal.spawn.SpawnService;
import fr.elias.oreoEssentials.player.internal.tp.rabbit.brokers.TpCrossServerBroker;
import fr.elias.oreoEssentials.player.internal.tp.rabbit.brokers.TpaCrossServerBroker;
import fr.elias.oreoEssentials.player.internal.tp.service.TeleportService;
import fr.elias.oreoEssentials.trade.internal.rabbit.TradeCrossServerBroker;
import fr.elias.oreoEssentials.trade.internal.service.TradeService;
import fr.elias.oreoEssentials.storage.internal.warps.WarpService;
import fr.elias.oreoEssentials.storage.internal.warps.rabbit.WarpDirectory;
import fr.elias.oreoEssentials.economy.internal.offline.OfflinePlayerCache;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.PacketManager;
import fr.elias.oreoEssentials.configuration.internal.services.FreezeService;
import fr.elias.oreoEssentials.player.internal.services.GodService;
import fr.elias.oreoEssentials.guards.internal.services.JoinFloodGuardService;
import fr.elias.oreoEssentials.player.internal.services.MessageService;
import fr.elias.oreoEssentials.storage.internal.services.StorageApi;
import fr.elias.oreoEssentials.player.internal.services.VanishService;
import fr.elias.oreoEssentials.platform.scheduling.OreScheduler;
import fr.elias.oreoEssentials.proxy.internal.transport.ProxyMessenger;
import fr.minuskube.inv.InventoryManager;
import java.util.Map;
import java.util.UUID;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class OreoEssentials extends JavaPlugin {
    private static OreoEssentials instance;
    private final OreoModuleRegistry moduleRegistry = new OreoModuleRegistry();
    private PaperModulith modulith;

    public OreoModuleRegistry getModuleRegistry() { return moduleRegistry; }
    public static OreoEssentials get() { return instance; }

    @Override
    public void onLoad() { loadExternalLibraries(); }

    @Override
    public void onEnable() {
        instance = this;
        try {
            modulith = OreoModules.start(this);
            getLogger().info("OreoEssentials enabled with " + modulith.runtime().modules().size() + " modules.");
        } catch (RuntimeException failure) {
            getLogger().log(java.util.logging.Level.SEVERE, "OreoEssentials module startup failed", failure);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        OreScheduler.cancelAll(this);
        try {
            if (modulith != null) modulith.close();
        } catch (RuntimeException failure) {
            getLogger().log(java.util.logging.Level.SEVERE, "OreoEssentials module shutdown failed", failure);
        } finally {
            modulith = null;
            org.bukkit.event.HandlerList.unregisterAll(this);
            getServer().getServicesManager().unregisterAll(this);
            moduleRegistry.clear();
            instance = null;
        }
        getLogger().info("OreoEssentials disabled.");
    }

    public dev.oreo.modulith.core.RuntimeDiagnostics getModuleDiagnostics() {
        return modulith == null ? null : modulith.runtime().diagnostics();
    }

    public MuteService getMuteService() { return moduleRegistry.read(MuteSystemServices.class, MuteSystemServices::getMuteService); }

    public fr.elias.oreoEssentials.ignore.internal.IgnoreService getIgnoreService() { return moduleRegistry.read(IgnoreServices.class, IgnoreServices::getIgnoreService); }

    public fr.elias.oreoEssentials.warnings.internal.WarnService getWarnService() { return moduleRegistry.read(WarningsServices.class, WarningsServices::getWarnService); }

    public fr.elias.oreoEssentials.mail.internal.MailService getMailService() { return moduleRegistry.read(MailServices.class, MailServices::getMailService); }

    public com.mongodb.client.MongoClient getHomesMongoClient() { return moduleRegistry.read(StorageServices.class, StorageServices::getHomesMongoClient); }

    public fr.elias.oreoEssentials.punishment.internal.PunishmentLogger getPunishmentLogger() { return moduleRegistry.read(PunishmentLoggerServices.class, PunishmentLoggerServices::getPunishmentLogger); }

    public fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig getSettingsConfig() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getSettingsConfig); }

    public JoinFloodGuardService getJoinFloodGuardService() { return moduleRegistry.read(PerformanceGuardsServices.class, PerformanceGuardsServices::getJoinFloodGuardService); }

    public ShopModule getShopModule() { return moduleRegistry.read(ShopServices.class, ShopServices::getShopModule); }

    public fr.elias.oreoEssentials.integrations.internal.integration.DiscordModerationNotifier getDiscordMod() { return moduleRegistry.read(DiscordIntegrationServices.class, DiscordIntegrationServices::getDiscordMod); }

    public TpCrossServerBroker getTpBroker() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getTpBroker); }

    public fr.elias.oreoEssentials.portals.internal.PortalsManager getPortalsManager() { return moduleRegistry.read(PortalsServices.class, PortalsServices::getPortals); }

    public fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsManager getJumpPadsManager() { return moduleRegistry.read(JumpPadsServices.class, JumpPadsServices::getJumpPads); }

    public fr.elias.oreoEssentials.modgui.internal.ModGuiService getModGuiService() { return moduleRegistry.read(ModGuiServices.class, ModGuiServices::getModGuiService); }

    public AuctionHouseModule getAuctionHouseModule() { return moduleRegistry.read(InventoriesServices.class, InventoriesServices::getAuctionHouse); }

    public fr.elias.oreoEssentials.inventory.internal.orders.OrdersModule getOrdersModule() { return moduleRegistry.read(InventoriesServices.class, InventoriesServices::getOrdersModule); }

    public fr.elias.oreoEssentials.discord.internal.discordbot.DiscordLinkManager getDiscordLinkManager() { return moduleRegistry.read(DiscordIntegrationServices.class, DiscordIntegrationServices::getDiscordLinkManager); }

    public CurrencyService getCurrencyService() { return moduleRegistry.read(CurrencySystemServices.class, CurrencySystemServices::getCurrencyService); }

    public CurrencyConfig getCurrencyConfig() { return moduleRegistry.read(CurrencySystemServices.class, CurrencySystemServices::getCurrencyConfig); }

    public fr.elias.oreoEssentials.chat.internal.channels.ChatChannelManager getChannelManager() { return moduleRegistry.read(ChatServices.class, ChatServices::getChannelManager); }

    public fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyService getTempFlyService() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getTempFlyService); }

    public EconomyBootstrap getEconomy() { return moduleRegistry.read(EconomyServices.class, EconomyServices::getEcoBootstrap); }

    public EconomyBootstrap getEcoBootstrap() { return moduleRegistry.read(EconomyServices.class, EconomyServices::getEcoBootstrap); }

    public InventoryManager getInvManager() { return moduleRegistry.read(InventoriesServices.class, InventoriesServices::getInvManager); }

    public InventoryManager getInventoryManager() { return moduleRegistry.read(InventoriesServices.class, InventoriesServices::getInvManager); }

    public AutoRebootService getAutoRebootService() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getAutoRebootService); }

    public CraftActionsConfig getCraftActionsConfig() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getCraftActionsConfig); }

    public SellGuiManager getSellGuiManager() { return moduleRegistry.read(InventoriesServices.class, InventoriesServices::getSellGuiManager); }

    public fr.elias.oreoEssentials.maintenance.internal.MaintenanceService getMaintenanceService() { return moduleRegistry.read(MaintenanceServices.class, MaintenanceServices::getMaintenanceService); }

    public ProxyMessenger getProxyMessenger() { return moduleRegistry.read(ProxyMessagingServices.class, ProxyMessagingServices::getProxyMessenger); }

    public  fr.elias.oreoEssentials.grouprtp.internal.GroupRtpModule getGroupRtpModule() { return moduleRegistry.read(GroupRtpServices.class, GroupRtpServices::getGroupRtpModule); }

    public TpaCrossServerBroker getTpaBroker() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getTpaBroker); }

    public fr.elias.oreoEssentials.kits.internal.KitsManager getKitsManager() { return moduleRegistry.read(KitsServices.class, KitsServices::getKitsManager); }

    public fr.elias.oreoEssentials.tab.internal.TabListManager getTabListManager() { return moduleRegistry.read(TabServices.class, TabServices::getTabListManager); }

    public fr.elias.oreoEssentials.bossbar.internal.BossBarService getBossBarService() { return moduleRegistry.read(BossBarServices.class, BossBarServices::getBossBarService); }

    public fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsService getPlayervaultsService() { return moduleRegistry.read(PlayerVaultsServices.class, PlayerVaultsServices::getPlayervaultsService); }

    public InvseeService getInvseeService() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getInvseeService); }

    public fr.elias.oreoEssentials.commands.internal.ic.ICManager getIcManager() { return moduleRegistry.read(CommandsServices.class, CommandsServices::getIcManager); }

    public fr.elias.oreoEssentials.playtime.internal.PlaytimeRewardsService getPlaytimeRewards() { return moduleRegistry.read(PlaytimeServices.class, PlaytimeServices::getPlaytimeRewards); }

    public TradeCrossServerBroker getTradeBroker() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getTradeBroker); }

    public TradeService getTradeService() { return moduleRegistry.read(TradeServices.class, TradeServices::getTradeService); }

    public AfkService getAfkService() { return moduleRegistry.read(AfkServices.class, AfkServices::getAfkService); }

    public AfkPoolService getAfkPoolService() { return moduleRegistry.read(AfkServices.class, AfkServices::getAfkPoolService); }

    public fr.elias.oreoEssentials.configuration.internal.config.CrossServerSettings getCrossServerSettings() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getCrossServerSettings); }

    public fr.elias.oreoEssentials.enderchest.internal.EnderChestService getEnderChestService() { return moduleRegistry.read(EnderChestServices.class, EnderChestServices::getEcService); }

    public RtpConfig getRtpConfig() { return moduleRegistry.read(RtpServices.class, RtpServices::getRtpConfig); }

    public ClearLagManager getClearLagManager() { return moduleRegistry.read(ClearLagServices.class, ClearLagServices::getClearLag); }

    public fr.elias.oreoEssentials.commandcontrol.internal.CommandControlService getCommandControlService() { return moduleRegistry.read(CommandControlServices.class, CommandControlServices::getCommandControlService); }

    public fr.elias.oreoEssentials.daily.internal.DailyService getDailyService() { return moduleRegistry.read(DailyServices.class, DailyServices::getDailyService); }

    public fr.elias.oreoEssentials.commandcontrol.internal.aliases.AliasService getAliasService() { return moduleRegistry.read(ProxyMessagingServices.class, ProxyMessagingServices::getAliasService); }

    public fr.elias.oreoEssentials.jails.internal.jail.JailService getJailService() { return moduleRegistry.read(JailsServices.class, JailsServices::getJailService); }

    public CustomCraftingService getCustomCraftingService() { return moduleRegistry.read(CustomCraftingServices.class, CustomCraftingServices::getCustomCraftingService); }

    public fr.elias.oreoEssentials.nametag.internal.MultiBossBarService getMultiBossBarService() { return moduleRegistry.read(NametagServices.class, NametagServices::getMultiBossBarService); }

    public fr.elias.oreoEssentials.nametag.internal.CustomNameplatesConfig getCustomNameplatesConfig() { return moduleRegistry.read(NametagServices.class, NametagServices::getCustomNameplatesConfig); }

    public BackBroker getBackBroker() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getBackBroker); }

    public Gson getGson() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getGson); }

    public Map<UUID, BackLocation> getPendingBackTeleports() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getPendingBackTeleports); }

    public VanishService getVanishService() {
        return moduleRegistry.read(PlayerServices.class, PlayerServices::getVanishService);
    }
    public void reloadCustomNameplates() { moduleRegistry.require(NametagServices.class).reloadCustomNameplates(); }

    private void loadExternalLibraries() {
        try {
            net.byteflux.libby.BukkitLibraryManager libraryManager = new net.byteflux.libby.BukkitLibraryManager(this);
            libraryManager.addMavenCentral();
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("org.mongodb").artifactId("bson").version("5.1.0").build());
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("org.mongodb").artifactId("mongodb-driver-core").version("5.1.0").build());
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("org.mongodb").artifactId("mongodb-driver-sync").version("5.1.0").build());
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("org.apache.commons").artifactId("commons-pool2").version("2.12.0").build());
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("redis.clients").artifactId("jedis").version("5.1.0").build());
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("org.postgresql").artifactId("postgresql").version("42.7.4").build());
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("com.rabbitmq").artifactId("amqp-client").version("5.15.0").build());
            libraryManager.loadLibrary(net.byteflux.libby.Library.builder().groupId("org.xerial").artifactId("sqlite-jdbc").version("3.46.1.3").build());
            getLogger().info("[LibraryLoader] All dependencies loaded successfully!");
        } catch (Exception e) {
            getLogger().severe("[LibraryLoader] FAILED to load dependencies: " + e.getMessage());
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    // -------------------------------------------------------------------------
    // Utility
    // -------------------------------------------------------------------------

    public boolean isMessagingAvailable() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getPacketManager) != null && moduleRegistry.read(MessagingServices.class, MessagingServices::getPacketManager).isInitialized(); }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    public IpTracker getIpTracker() { return moduleRegistry.read(NotesServices.class, NotesServices::getIpTracker); }

    public FreezeManager getFreezeManager() { return moduleRegistry.read(FreezeServices.class, FreezeServices::getFreezeManager); }

    public PlayerNotesManager getNotesManager() { return moduleRegistry.read(NotesServices.class, NotesServices::getNotesManager); }

    public NotesChatListener getNotesChat() { return moduleRegistry.read(NotesServices.class, NotesServices::getNotesChat); }

    public ConfigService getConfigService() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getConfigService); }

    public StorageApi getStorage() { return moduleRegistry.read(StorageServices.class, StorageServices::getStorage); }

    public SpawnService getSpawnService() { return moduleRegistry.read(StorageServices.class, StorageServices::getSpawnService); }

    public WarpService getWarpService() { return moduleRegistry.read(StorageServices.class, StorageServices::getWarpService); }

    public HomeService getHomeService() { return moduleRegistry.read(StorageServices.class, StorageServices::getHomeService); }

    public PlayerWarpService getPlayerWarpService() { return moduleRegistry.read(StorageServices.class, StorageServices::getPlayerWarpService); }

    public PlayerWarpDirectory getPlayerWarpDirectory() { return moduleRegistry.read(StorageServices.class, StorageServices::getPlayerWarpDirectory); }

    public InvlookManager getInvlookManager() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getInvlookManager); }

    public TeleportService getTeleportService() { return moduleRegistry.read(PlayerServices.class, PlayerServices::getTeleportService); }

    public BackService getBackService() { return moduleRegistry.read(PlayerServices.class, PlayerServices::getBackService); }

    public MessageService getMessageService() { return moduleRegistry.read(PlayerServices.class, PlayerServices::getMessageService); }

    public DeathBackService getDeathBackService() { return moduleRegistry.read(PlayerServices.class, PlayerServices::getDeathBackService); }

    public GodService getGodService() { return moduleRegistry.read(PlayerServices.class, PlayerServices::getGodService); }

    public CommandManager getCommands() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getCommands); }

    public ChatSyncManager getChatSyncManager() { return moduleRegistry.read(ChatServices.class, ChatServices::getChatSyncManager); }

    public fr.elias.oreoEssentials.mobs.internal.HealthBarListener getHealthBarListener() { return moduleRegistry.read(MobsServices.class, MobsServices::getHealthBarListener); }

    public FreezeService getFreezeService() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getFreezeService); }

    public fr.elias.oreoEssentials.chat.internal.CustomConfig getChatConfig() { return moduleRegistry.read(ChatServices.class, ChatServices::getChatConfig); }

    public org.bukkit.configuration.file.FileConfiguration getPlayerWarpsConfig() { return moduleRegistry.read(StorageServices.class, StorageServices::getPlayerWarpsConfig); }
    public void reloadPlayerWarpsConfig() { moduleRegistry.require(StorageServices.class).reloadPlayerWarpsConfig(); }

    public fr.elias.oreoEssentials.daily.internal.DailyConfig getDailyConfig() { return moduleRegistry.read(DailyServices.class, DailyServices::getDailyConfig); }

    public fr.elias.oreoEssentials.daily.internal.RewardsConfig getDailyRewardsConfig() { return moduleRegistry.read(DailyServices.class, DailyServices::getDailyRewardsConfig); }
    public void reloadChat() { moduleRegistry.require(ChatServices.class).reloadChat(); }

    public WarpDirectory getWarpDirectory() { return moduleRegistry.read(StorageServices.class, StorageServices::getWarpDirectory); }

    public SpawnDirectory getSpawnDirectory() { return moduleRegistry.read(StorageServices.class, StorageServices::getSpawnDirectory); }

    public fr.elias.oreoEssentials.storage.internal.directory.PlayerDirectory getPlayerDirectory() { return moduleRegistry.read(StorageServices.class, StorageServices::getPlayerDirectory); }

    public TeleportBroker getTeleportBroker() { return moduleRegistry.read(PlayerServices.class, PlayerServices::getTeleportBroker); }

    public RedisManager getRedis() { return moduleRegistry.read(RedisServices.class, RedisServices::getRedis); }
    public OfflinePlayerCache getOfflinePlayerCache() { return moduleRegistry.require(EconomyServices.class).getOfflinePlayerCache(); }

    public PlayerEconomyDatabase getDatabase() { return moduleRegistry.read(EconomyServices.class, EconomyServices::getDatabase); }

    public PacketManager getPacketManager() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getPacketManager); }

    public ScoreboardService getScoreboardService() { return moduleRegistry.read(ScoreboardServices.class, ScoreboardServices::getScoreboardService); }

    public fr.elias.oreoEssentials.storage.internal.homes.HomeTeleportBroker getHomeTeleportBroker() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getHomeTpBroker); }

    public fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsService getPlayerVaultsService() { return moduleRegistry.read(PlayerVaultsServices.class, PlayerVaultsServices::getPlayervaultsService); }

    public fr.elias.oreoEssentials.playtime.internal.PlaytimeRewardsService getPlaytimeRewardsService() { return moduleRegistry.read(PlaytimeServices.class, PlaytimeServices::getPlaytimeRewards); }

    public ModBridge getModBridge() { return moduleRegistry.read(MessagingServices.class, MessagingServices::getModBridge); }

    public java.util.Map<java.util.UUID, Long> getRtpCooldownCache() { return moduleRegistry.read(RtpServices.class, RtpServices::getRtpCooldownCache); }

    public fr.elias.oreoEssentials.playtime.internal.PlaytimeTracker getPlaytimeTracker() { return moduleRegistry.read(PlaytimeServices.class, PlaytimeServices::getPlaytimeTracker); }

    public PlayerNametagManager getNametagManager() { return moduleRegistry.read(NametagServices.class, NametagServices::getNametagManager); }

    public fr.elias.oreoEssentials.dialogs.internal.dialogs.OreoDialogManager getDialogManager() { return moduleRegistry.read(DialogsServices.class, DialogsServices::getDialogManager); }

    public fr.elias.oreoEssentials.nametag.internal.ChatBubbleService getChatBubbleService() { return moduleRegistry.read(NametagServices.class, NametagServices::getChatBubbleService); }

    public SettingsConfig getSettings() { return moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getSettings); }

    public boolean isJoinUiReady(Player player) {
        return moduleRegistry.read(PerformanceGuardsServices.class, PerformanceGuardsServices::getJoinFloodGuardService) == null || moduleRegistry.read(PerformanceGuardsServices.class, PerformanceGuardsServices::getJoinFloodGuardService).isUiReady(player);
    }

    public long getJoinUiDelayTicks(Player player, long minimumDelayTicks) {
        return moduleRegistry.read(PerformanceGuardsServices.class, PerformanceGuardsServices::getJoinFloodGuardService) == null
                ? Math.max(0L, minimumDelayTicks)
                : moduleRegistry.read(PerformanceGuardsServices.class, PerformanceGuardsServices::getJoinFloodGuardService).getDeferredJoinDelayTicks(player, minimumDelayTicks);
    }

    public RtpPendingService getRtpPendingService() { return moduleRegistry.read(RtpServices.class, RtpServices::getRtpPendingService); }

    public fr.elias.oreoEssentials.rtp.internal.RtpCrossServerBridge getRtpBridge() { return moduleRegistry.read(RtpServices.class, RtpServices::getRtpBridge); }

    public fr.elias.oreoEssentials.shards.internal.OreoShardsModule getShardsModule() { return moduleRegistry.read(ShardingServices.class, ShardingServices::getShardsModule); }

    public CommandToggleService getCommandToggleService() { return moduleRegistry.read(CommandToggleServices.class, CommandToggleServices::getCommandToggleService); }

    public Economy getVaultEconomy() { return moduleRegistry.read(EconomyServices.class, EconomyServices::getVaultEconomy); }

    public CurrencyPlaceholderExpansion getCurrencyPlaceholders() { return moduleRegistry.read(IntegrationsServices.class, IntegrationsServices::getCurrencyPlaceholders); }

    public String getServerNameSafe() {
        try {
            if (moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getConfigService) != null) { String name = moduleRegistry.read(ConfigurationServices.class, ConfigurationServices::getConfigService).serverName(); if (name != null && !name.isBlank()) return name; }
        } catch (Throwable ignored) {}
        try { String name = getServer().getName(); if (name != null && !name.isBlank()) return name; } catch (Throwable ignored) {}
        return "UNKNOWN";
    }

    public final class PapiUtil {
        private PapiUtil() {}
        public static String apply(Player p, String text) {
            if (text == null) return "";
            if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) return text;
            try { return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, text); }
            catch (Throwable t) { return text; }
        }
    }
}
