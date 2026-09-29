package fr.elias.oreoEssentials.freeze.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.freeze.internal.freeze.FreezeManager;

@PluginModule(value = "freeze", dependencies = {"custom-crafting"})
public final class FreezeModule extends ManagedModule implements FreezeServices {
    private FreezeManager freezeManager;

    @Override
    protected void start() {
        initFreeze();
    }

    @Override public FreezeManager getFreezeManager() { return freezeManager; }

    private void initFreeze() {
        this.freezeManager = new FreezeManager(plugin);
    }
}
