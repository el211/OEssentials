package fr.elias.oreoEssentials.ignore.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;

@PluginModule(value = "ignore", dependencies = {"help"})
public final class IgnoreModule extends ManagedModule implements IgnoreServices {
    private fr.elias.oreoEssentials.ignore.internal.IgnoreService ignoreService;

    @Override
    protected void start() {
        initIgnore();
    }

    @Override public fr.elias.oreoEssentials.ignore.internal.IgnoreService getIgnoreService() { return ignoreService; }

    private void initIgnore() {
        try {
            this.ignoreService = new fr.elias.oreoEssentials.ignore.internal.IgnoreService(plugin);
            plugin.getLogger().info("[Ignore] Initialized.");
        } catch (Throwable t) {
            plugin.getLogger().severe("[Ignore] Failed to initialize: " + t.getMessage());
        }
    }
}
