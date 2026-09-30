package fr.elias.oessentials.commands.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface CommandsServices {
    fr.elias.oessentials.commands.internal.ic.ICManager getIcManager();
}
