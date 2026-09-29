package fr.elias.oreoEssentials.dialogs.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface DialogsServices {
    fr.elias.oreoEssentials.dialogs.internal.dialogs.OreoDialogManager getDialogManager();
}
