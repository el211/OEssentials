package fr.elias.oreoEssentials.scoreboard.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.BootstrapSupport;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.scoreboard.internal.ScoreboardConfig;
import fr.elias.oreoEssentials.scoreboard.internal.ScoreboardService;
import fr.elias.oreoEssentials.scoreboard.internal.ScoreboardToggleCommand;

@PluginModule(value = "scoreboard", dependencies = {"boss-bar", "configuration::services"})
public final class ScoreboardModule extends ManagedModule implements ScoreboardServices {
    private ScoreboardService scoreboardService;

    @Override
    protected void start() {
        cleanup("scoreboardService", () -> { if (scoreboardService != null) scoreboardService.stop(); });
        initScoreboard();
    }

    @Override public ScoreboardService getScoreboardService() { return scoreboardService; }

    private void initScoreboard() {
        if (services(ConfigurationServices.class).getSettingsConfig().scoreboardEnabled() && BootstrapSupport.uiModuleAllowed(plugin, "scoreboard")) {
            ScoreboardConfig sbCfg = ScoreboardConfig.load(plugin);
            this.scoreboardService = new ScoreboardService(plugin, sbCfg);
            this.scoreboardService.start();
            services(ConfigurationServices.class).getCommands().register(new ScoreboardToggleCommand(this.scoreboardService));
            plugin.getLogger().info("[Scoreboard] Enabled.");
        } else {
            BootstrapSupport.unregisterCommandHard(plugin, "scoreboard");
            BootstrapSupport.unregisterCommandHard(plugin, "sb");
            plugin.getLogger().info("[Scoreboard] Disabled via settings.yml");
        }
    }
}
