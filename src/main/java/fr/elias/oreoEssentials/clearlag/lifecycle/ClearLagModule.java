package fr.elias.oreoEssentials.clearlag.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.BootstrapSupport;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.clearlag.internal.ClearLagManager;

@PluginModule(value = "clear-lag", dependencies = {"configuration::services", "mobs"})
public final class ClearLagModule extends ManagedModule implements ClearLagServices {
    private ClearLagManager clearLag;

    @Override
    protected void start() {
        cleanup("clearLag", () -> { if (clearLag != null) clearLag.shutdown(); });
        initClearLag();
    }

    @Override public ClearLagManager getClearLag() { return clearLag; }

    private void initClearLag() {
        if (services(ConfigurationServices.class).getSettingsConfig().clearLagEnabled()) {
            try {
                this.clearLag = new ClearLagManager(plugin);
                var olagg = new fr.elias.oreoEssentials.clearlag.internal.ClearLagCommands(clearLag);
                services(ConfigurationServices.class).getCommands().registerLegacy("olagg", olagg);
                plugin.getLogger().info("[OreoLag] Enabled - /olagg active.");
            } catch (Throwable t) {
                plugin.getLogger().warning("[OreoLag] FAILED to initialize: " + t.getMessage());
                this.clearLag = null;
            }
        } else {
            BootstrapSupport.unregisterCommandHard(plugin, "olagg");
            this.clearLag = null;
            plugin.getLogger().info("[OreoLag] Disabled by settings.yml.");
        }
    }
}
