package fr.elias.oreoEssentials.warnings.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;

@PluginModule(value = "warnings", dependencies = {"ignore"})
public final class WarningsModule extends ManagedModule implements WarningsServices {
    private fr.elias.oreoEssentials.warnings.internal.WarnService warnService;

    @Override
    protected void start() {
        initWarnings();
    }

    @Override public fr.elias.oreoEssentials.warnings.internal.WarnService getWarnService() { return warnService; }

    private void initWarnings() {
        try {
            this.warnService = new fr.elias.oreoEssentials.warnings.internal.WarnService(plugin);
            plugin.getLogger().info("[Warnings] Initialized.");
        } catch (Throwable t) {
            plugin.getLogger().severe("[Warnings] Failed to initialize: " + t.getMessage());
        }
    }
}
