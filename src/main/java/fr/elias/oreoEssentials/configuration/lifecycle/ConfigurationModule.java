package fr.elias.oreoEssentials.configuration.lifecycle;

import com.google.gson.Gson;
import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.BootstrapSupport;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.platform.commands.CommandManager;
import fr.elias.oreoEssentials.configuration.internal.config.ConfigService;
import fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig;
import fr.elias.oreoEssentials.configuration.internal.listeners.FreezeListener;
import fr.elias.oreoEssentials.configuration.internal.autoreboot.AutoRebootService;
import fr.elias.oreoEssentials.crafting.internal.customcraft.CraftActionsConfig;
import fr.elias.oreoEssentials.configuration.internal.invlook.listeners.InvlookListener;
import fr.elias.oreoEssentials.configuration.internal.invlook.manager.InvlookManager;
import fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyCommand;
import fr.elias.oreoEssentials.configuration.internal.services.FreezeService;
import fr.elias.oreoEssentials.configuration.internal.logging.KillallLogger;
import fr.elias.oreoEssentials.shared.Lang;
import java.io.File;

@PluginModule(value = "configuration")
public final class ConfigurationModule extends ManagedModule implements ConfigurationServices {
    private fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig settingsConfig;
    private fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyService tempFlyService;
    private fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyConfig tempFlyConfig;
    private ConfigService configService;
    private AutoRebootService autoRebootService;
    private CraftActionsConfig craftActionsConfig;
    private CommandManager commands;
    private FreezeService freezeService;
    private KillallLogger killallLogger;
    private SettingsConfig settings;
    private boolean economyEnabled;
    private boolean redisEnabled;
    private boolean rabbitEnabled;
    private InvlookManager invlookManager;
    private fr.elias.oreoEssentials.configuration.internal.config.CrossServerSettings crossServerSettings;
    private Gson gson = new Gson();

    @Override
    protected void start() {
        cleanup("commands", () -> { if (commands != null) commands.shutdown(); });
        cleanup("autoRebootService", () -> { if (autoRebootService != null) autoRebootService.stop(); });
        cleanup("tempFlyService", () -> { if (tempFlyService != null) tempFlyService.shutdown(); });
        cleanup("invlookManager", () -> { if (invlookManager != null) invlookManager.clear(); });
        initConfig();
    }

    @Override public fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig getSettingsConfig() { return settingsConfig; }
    @Override public fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyService getTempFlyService() { return tempFlyService; }
    @Override public fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyConfig getTempFlyConfig() { return tempFlyConfig; }
    @Override public ConfigService getConfigService() { return configService; }
    @Override public AutoRebootService getAutoRebootService() { return autoRebootService; }
    @Override public CraftActionsConfig getCraftActionsConfig() { return craftActionsConfig; }
    @Override public CommandManager getCommands() { return commands; }
    @Override public FreezeService getFreezeService() { return freezeService; }
    @Override public KillallLogger getKillallLogger() { return killallLogger; }
    @Override public SettingsConfig getSettings() { return settings; }
    @Override public boolean getEconomyEnabled() { return economyEnabled; }
    @Override public boolean getRedisEnabled() { return redisEnabled; }
    @Override public boolean getRabbitEnabled() { return rabbitEnabled; }
    @Override public InvlookManager getInvlookManager() { return invlookManager; }
    @Override public fr.elias.oreoEssentials.configuration.internal.config.CrossServerSettings getCrossServerSettings() { return crossServerSettings; }
    @Override public Gson getGson() { return gson; }

    // Init methods

    private void initConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.getDataFolder().mkdirs();
            plugin.saveDefaultConfig();
            plugin.getLogger().info("[Config] Created default config.yml");
        } else {
            plugin.getLogger().info("[Config] Loading existing config.yml");
        }

        fr.elias.oreoEssentials.configuration.internal.config.LegacySettingsMigrator.migrate(plugin);
        fr.elias.oreoEssentials.configuration.internal.migration.ScoreboardTabMigrator.migrate(plugin);
        fr.elias.oreoEssentials.configuration.internal.migration.ChatMessagingMigrator.migrate(plugin);
        fr.elias.oreoEssentials.configuration.internal.migration.FeatureConfigMigrator.migrate(plugin);
        plugin.reloadConfig();

        this.settingsConfig = new fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig(plugin);
        this.settings = this.settingsConfig;

        showStartupBanner();

        this.configService    = new ConfigService(plugin);
        this.crossServerSettings = fr.elias.oreoEssentials.configuration.internal.config.CrossServerSettings.load(plugin);
        this.economyEnabled   = settingsConfig.economyEnabled();
        this.redisEnabled     = plugin.getConfig().getBoolean("redis.enabled", false);
        this.rabbitEnabled    = plugin.getConfig().getBoolean("rabbitmq.enabled", false);
        this.invlookManager   = new InvlookManager();
        this.killallLogger    = new KillallLogger(plugin);

        plugin.getLogger().info("[Economy] " + (economyEnabled ? "Enabled" : "Disabled") + " via settings.yml");

        this.autoRebootService = new AutoRebootService(plugin);
        this.autoRebootService.start();

        this.craftActionsConfig = new CraftActionsConfig(plugin);
        this.freezeService = new FreezeService();
        plugin.getServer().getPluginManager().registerEvents(new FreezeListener(freezeService), plugin);

        this.commands = new CommandManager(plugin);

        plugin.getServer().getPluginManager().registerEvents(new InvlookListener(plugin), plugin);

        Lang.init(plugin);

        if (settingsConfig.isEnabled("tempfly")) {
            this.tempFlyConfig  = new fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyConfig(plugin.getDataFolder());
            this.tempFlyService = new fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyService(plugin, tempFlyConfig);
            var tempFlyCmd = new TempFlyCommand(tempFlyService);
            this.commands.register(tempFlyCmd);
            commands.rewireTab("tempfly", tempFlyCmd);
            commands.rewireTab("tfly",    tempFlyCmd);
            plugin.getLogger().info("[TempFly] Enabled.");
        } else {
            BootstrapSupport.unregisterCommandHard(plugin, "tempfly");
            BootstrapSupport.unregisterCommandHard(plugin, "tfly");
            plugin.getLogger().info("[TempFly] Disabled by settings.yml.");
        }

        this.commands.register(new fr.elias.oreoEssentials.commands.internal.commands.core.admins.OeSettingsCommand(plugin));
        plugin.getLogger().info("[Settings] GUI command registered (/oesettings).");

        plugin.getLogger().info("[BOOT] storage=" + plugin.getConfig().getString("essentials.storage", "yaml")
                + " economyType=" + plugin.getConfig().getString("economy.type", "none")
                + " redis=" + redisEnabled + " rabbit=" + rabbitEnabled
                + " server.name=" + configService.serverName());
    }

    // -------------------------------------------------------------------------
    // Banners / diagnostics
    // -------------------------------------------------------------------------

    private void showStartupBanner() {
        String version = plugin.getDescription().getVersion();
        plugin.getLogger().info("+------------------------------------------------------------+");
        plugin.getLogger().info("|          STARTING OREOESSENTIALS PREMIUM                   |");
        plugin.getLogger().info("|  Version: " + String.format("%-50s", version) + "|");
        plugin.getLogger().info("|          Loading all features and modules...               |");
        plugin.getLogger().info("+------------------------------------------------------------+");
    }
}
