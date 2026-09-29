package fr.elias.oreoEssentials.commands.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface CommandsServices {
    fr.elias.oreoEssentials.commands.internal.ic.ICManager getIcManager();
}
