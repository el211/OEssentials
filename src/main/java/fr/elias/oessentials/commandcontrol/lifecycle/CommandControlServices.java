package fr.elias.oessentials.commandcontrol.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface CommandControlServices {
    fr.elias.oessentials.commandcontrol.internal.CommandControlService getCommandControlService();
}
