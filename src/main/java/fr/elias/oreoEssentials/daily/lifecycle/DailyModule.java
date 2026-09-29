package fr.elias.oreoEssentials.daily.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "daily", dependencies = {"configuration::services", "freeze"})
public final class DailyModule extends ManagedModule implements DailyServices {
    private fr.elias.oreoEssentials.daily.internal.DailyConfig dailyConfig;
    private fr.elias.oreoEssentials.daily.internal.RewardsConfig dailyRewardsConfig;
    private fr.elias.oreoEssentials.daily.internal.DailyService dailyService;
    private fr.elias.oreoEssentials.daily.internal.DailyMongoStore dailyStore;

    @Override
    protected void start() {
        cleanup("dailyStore", () -> { if (dailyStore != null) dailyStore.close(); });
        initDaily();
    }

    @Override public fr.elias.oreoEssentials.daily.internal.DailyConfig getDailyConfig() { return dailyConfig; }
    @Override public fr.elias.oreoEssentials.daily.internal.RewardsConfig getDailyRewardsConfig() { return dailyRewardsConfig; }
    @Override public fr.elias.oreoEssentials.daily.internal.DailyService getDailyService() { return dailyService; }
    @Override public fr.elias.oreoEssentials.daily.internal.DailyMongoStore getDailyStore() { return dailyStore; }

    private void initDaily() {
        this.dailyConfig = new fr.elias.oreoEssentials.daily.internal.DailyConfig(plugin);
        this.dailyConfig.load();
        fr.elias.oreoEssentials.daily.internal.DailyStorage dailyStorage = fr.elias.oreoEssentials.daily.internal.DailyStorage.create(plugin, dailyConfig);
        this.dailyRewardsConfig = new fr.elias.oreoEssentials.daily.internal.RewardsConfig(plugin);
        this.dailyRewardsConfig.load();
        this.dailyService = new fr.elias.oreoEssentials.daily.internal.DailyService(plugin, dailyConfig, dailyStorage, dailyRewardsConfig);
        var dailyCmd = new fr.elias.oreoEssentials.daily.internal.DailyCommand(plugin, dailyConfig, dailyService, dailyRewardsConfig);
        services(ConfigurationServices.class).getCommands().registerLegacy("daily", dailyCmd, dailyCmd);
        plugin.getLogger().info("[Daily] Rewards system initialized with " + (dailyConfig.mongo.enabled ? "MongoDB" : "file-based") + " storage.");
    }
}
