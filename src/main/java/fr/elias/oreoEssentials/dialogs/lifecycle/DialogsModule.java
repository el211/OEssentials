package fr.elias.oreoEssentials.dialogs.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;

@PluginModule(value = "dialogs", dependencies = {"player-services"})
public final class DialogsModule extends ManagedModule implements DialogsServices {
    private fr.elias.oreoEssentials.dialogs.internal.dialogs.OreoDialogManager dialogManager;

    @Override
    protected void start() {
        initDialogs();
    }

    @Override public fr.elias.oreoEssentials.dialogs.internal.dialogs.OreoDialogManager getDialogManager() { return dialogManager; }

    private void initDialogs() { this.dialogManager = new fr.elias.oreoEssentials.dialogs.internal.dialogs.OreoDialogManager(plugin); }
}
