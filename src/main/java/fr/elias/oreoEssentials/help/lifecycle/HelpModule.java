package fr.elias.oreoEssentials.help.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "help", dependencies = {"configuration::services", "maintenance"})
public final class HelpModule extends ManagedModule implements HelpServices {

    @Override
    protected void start() {
        initHelp();
    }

    private void initHelp() {
        try {
            var helpConfig = new fr.elias.oreoEssentials.help.internal.HelpConfig(plugin);
            var helpCmd    = new fr.elias.oreoEssentials.help.internal.HelpCommand(helpConfig);
            services(ConfigurationServices.class).getCommands().register(helpCmd);
            plugin.getLogger().info("[Help] Initialized.");
        } catch (Throwable t) {
            plugin.getLogger().severe("[Help] Failed to initialize: " + t.getMessage());
        }
    }
}
