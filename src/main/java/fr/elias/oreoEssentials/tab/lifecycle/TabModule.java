package fr.elias.oreoEssentials.tab.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.BootstrapSupport;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "tab", dependencies = {"configuration::services", "scoreboard"})
public final class TabModule extends ManagedModule implements TabServices {
    private fr.elias.oreoEssentials.tab.internal.TabListManager tabListManager;

    @Override
    protected void start() {
        cleanup("tabListManager", () -> { if (tabListManager != null) tabListManager.stop(); });
        initTab();
    }

    @Override public fr.elias.oreoEssentials.tab.internal.TabListManager getTabListManager() { return tabListManager; }

    private void initTab() {
        if (services(ConfigurationServices.class).getSettingsConfig().tabEnabled() && BootstrapSupport.uiModuleAllowed(plugin, "tab")) {
            this.tabListManager = new fr.elias.oreoEssentials.tab.internal.TabListManager(plugin);
            this.tabListManager.start();
            plugin.getLogger().info("[TAB] Custom tab-list enabled.");
        } else {
            this.tabListManager = null;
            plugin.getLogger().info("[TAB] Disabled by settings.yml.");
        }
    }
}
