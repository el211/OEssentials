package fr.elias.oessentials.dialogs.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface DialogsServices {
    fr.elias.oessentials.dialogs.internal.dialogs.OreoDialogManager getDialogManager();
}
