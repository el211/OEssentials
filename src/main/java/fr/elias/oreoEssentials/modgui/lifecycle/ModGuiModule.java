package fr.elias.oreoEssentials.modgui.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.modgui.internal.world.WorldTweaksListener;

@PluginModule(value = "mod-gui", dependencies = {"inventories"})
public final class ModGuiModule extends ManagedModule implements ModGuiServices {
    private fr.elias.oreoEssentials.modgui.internal.ModGuiService modGuiService;

    @Override
    protected void start() {
        initModGui();
    }

    @Override public fr.elias.oreoEssentials.modgui.internal.ModGuiService getModGuiService() { return modGuiService; }

    private void initModGui() {
        try {
            this.modGuiService = new fr.elias.oreoEssentials.modgui.internal.ModGuiService(plugin);
            plugin.getLogger().info("[ModGUI] Server management GUI ready (/modgui).");
            new WorldTweaksListener(plugin);
        } catch (Throwable t) {
            plugin.getLogger().warning("[ModGUI] Failed to init: " + t.getMessage());
            this.modGuiService = null;
        }
    }
}
