package fr.elias.oreoEssentials.playtime.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "playtime", dependencies = {"configuration::services", "sharding"})
public final class PlaytimeModule extends ManagedModule implements PlaytimeServices {
    private fr.elias.oreoEssentials.playtime.internal.PlaytimeRewardsService playtimeRewards;
    private fr.elias.oreoEssentials.playtime.internal.PlaytimeTracker playtimeTracker;

    @Override
    protected void start() {
        cleanup("playtimeTracker", () -> { if (playtimeTracker != null) playtimeTracker.shutdown(); });
        initPlaytime();
    }

    @Override public fr.elias.oreoEssentials.playtime.internal.PlaytimeRewardsService getPlaytimeRewards() { return playtimeRewards; }
    @Override public fr.elias.oreoEssentials.playtime.internal.PlaytimeTracker getPlaytimeTracker() { return playtimeTracker; }

    private void initPlaytime() {
        this.playtimeTracker = new fr.elias.oreoEssentials.playtime.internal.PlaytimeTracker(plugin);
        this.playtimeRewards = new fr.elias.oreoEssentials.playtime.internal.PlaytimeRewardsService(plugin, playtimeTracker);
        this.playtimeRewards.init();

        if (!services(ConfigurationServices.class).getSettingsConfig().playtimeRewardsEnabled()) {
            this.playtimeRewards.setEnabled(false);
            plugin.getLogger().info("[Prewards] Disabled by settings.yml.");
        } else {
            plugin.getLogger().info("[Prewards] Enabled.");
        }

        var prewardsCmd = new fr.elias.oreoEssentials.playtime.internal.PrewardsCommand(plugin, this.playtimeRewards);
        services(ConfigurationServices.class).getCommands().registerLegacy("prewards", prewardsCmd, prewardsCmd);

        var playtimeCmd = new fr.elias.oreoEssentials.playtime.internal.commands.core.playercommands.PlaytimeCommand();
        services(ConfigurationServices.class).getCommands().registerLegacy("playtime", playtimeCmd, playtimeCmd);
    }
}
