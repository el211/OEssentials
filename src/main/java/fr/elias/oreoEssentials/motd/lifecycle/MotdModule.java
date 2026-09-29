package fr.elias.oreoEssentials.motd.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "motd", dependencies = {"configuration::services", "punishment-logger"})
public final class MotdModule extends ManagedModule implements MotdServices {

    @Override
    protected void start() {
        initMotd();
    }

    private void initMotd() {
        try {
            var motdConfig  = new fr.elias.oreoEssentials.motd.internal.MotdConfig(plugin);
            var motdService = new fr.elias.oreoEssentials.motd.internal.MotdService(plugin, motdConfig);
            plugin.getServer().getPluginManager().registerEvents(motdService, plugin);
            services(ConfigurationServices.class).getCommands().register(motdService);
            plugin.getLogger().info("[MOTD] Initialized.");
        } catch (Throwable t) {
            plugin.getLogger().severe("[MOTD] Failed to initialize: " + t.getMessage());
        }
    }
}
