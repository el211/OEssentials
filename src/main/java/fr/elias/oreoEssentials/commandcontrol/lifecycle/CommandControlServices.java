package fr.elias.oreoEssentials.commandcontrol.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface CommandControlServices {
    fr.elias.oreoEssentials.commandcontrol.internal.CommandControlService getCommandControlService();
}
