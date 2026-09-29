package fr.elias.oreoEssentials.commandtoggle.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.commandtoggle.internal.CommandToggleConfig;
import fr.elias.oreoEssentials.commandtoggle.internal.CommandToggleService;

@ModuleApi("services")
public interface CommandToggleServices {
    CommandToggleConfig getCommandToggleConfig();
    CommandToggleService getCommandToggleService();
}
