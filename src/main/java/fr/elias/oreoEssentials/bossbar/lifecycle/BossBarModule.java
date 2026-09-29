package fr.elias.oreoEssentials.bossbar.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.BootstrapSupport;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.bossbar.internal.BossBarService;
import fr.elias.oreoEssentials.bossbar.internal.BossBarToggleCommand;

@PluginModule(value = "boss-bar", dependencies = {"configuration::services", "rtp"})
public final class BossBarModule extends ManagedModule implements BossBarServices {
    private fr.elias.oreoEssentials.bossbar.internal.BossBarService bossBarService;

    @Override
    protected void start() {
        cleanup("bossBarService", () -> { if (bossBarService != null) bossBarService.stop(); });
        initBossBar();
    }

    @Override public fr.elias.oreoEssentials.bossbar.internal.BossBarService getBossBarService() { return bossBarService; }

    private void initBossBar() {
        if (services(ConfigurationServices.class).getSettingsConfig().bossbarEnabled() && BootstrapSupport.uiModuleAllowed(plugin, "bossbar")) {
            this.bossBarService = new BossBarService(plugin);
            this.bossBarService.start();
            services(ConfigurationServices.class).getCommands().register(new BossBarToggleCommand(this.bossBarService));
            plugin.getLogger().info("[BossBar] Enabled.");
        } else {
            BootstrapSupport.unregisterCommandHard(plugin, "bossbar");
            plugin.getLogger().info("[BossBar] Disabled by settings.yml.");
        }
    }
}
