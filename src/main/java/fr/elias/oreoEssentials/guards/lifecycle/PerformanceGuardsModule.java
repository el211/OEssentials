package fr.elias.oreoEssentials.guards.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.guards.internal.listeners.ContainerSpamGuardListener;
import fr.elias.oreoEssentials.guards.internal.services.JoinFloodGuardService;

@PluginModule(value = "performance-guards", dependencies = {"configuration::services"})
public final class PerformanceGuardsModule extends ManagedModule implements PerformanceGuardsServices {
    private JoinFloodGuardService joinFloodGuardService;

    @Override
    protected void start() {
        initPerformanceGuards();
    }

    @Override public JoinFloodGuardService getJoinFloodGuardService() { return joinFloodGuardService; }

    private void initPerformanceGuards() {
        this.joinFloodGuardService = new JoinFloodGuardService(plugin);
        plugin.getServer().getPluginManager().registerEvents(this.joinFloodGuardService, plugin);
        plugin.getServer().getPluginManager().registerEvents(new ContainerSpamGuardListener(plugin), plugin);

        plugin.getLogger().info("[Performance] Join flood protection "
                + (services(ConfigurationServices.class).getSettingsConfig().joinFloodProtectionEnabled() ? "enabled." : "disabled."));
        plugin.getLogger().info("[Performance] Container spam guard "
                + (services(ConfigurationServices.class).getSettingsConfig().containerSpamGuardEnabled() ? "enabled." : "disabled."));
    }
}
