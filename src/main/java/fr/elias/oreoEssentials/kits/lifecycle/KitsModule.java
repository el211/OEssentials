package fr.elias.oreoEssentials.kits.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.BootstrapSupport;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "kits", dependencies = {"command-toggle", "configuration::services"})
public final class KitsModule extends ManagedModule implements KitsServices {
    private fr.elias.oreoEssentials.kits.internal.KitsManager kitsManager;

    @Override
    protected void start() {
        cleanup("kitsManager", () -> { if (kitsManager != null) kitsManager.saveData(); });
        initKits();
    }

    @Override public fr.elias.oreoEssentials.kits.internal.KitsManager getKitsManager() { return kitsManager; }

    private void initKits() {
        boolean kitsFeature  = services(ConfigurationServices.class).getSettingsConfig().kitsEnabled();
        boolean kitsRegister = services(ConfigurationServices.class).getSettingsConfig().kitsCommandsEnabled();

        if (kitsFeature) {
            this.kitsManager = new fr.elias.oreoEssentials.kits.internal.KitsManager(plugin);
            if (kitsRegister) {
                new fr.elias.oreoEssentials.kits.internal.KitCommands(plugin, this.kitsManager);
                plugin.getLogger().info("[Kits] Loaded " + this.kitsManager.getKits().size() + " kits.");
            } else {
                BootstrapSupport.unregisterCommandHard(plugin, "kits");
                BootstrapSupport.unregisterCommandHard(plugin, "kit");
                plugin.getLogger().info("[Kits] Module loaded, commands NOT registered.");
            }
        } else {
            BootstrapSupport.unregisterCommandHard(plugin, "kits");
            BootstrapSupport.unregisterCommandHard(plugin, "kit");
            this.kitsManager = null;
            plugin.getLogger().info("[Kits] Disabled by config.");
        }
    }
}
