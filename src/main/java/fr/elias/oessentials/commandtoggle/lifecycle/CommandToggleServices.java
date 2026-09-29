package fr.elias.oessentials.commandtoggle.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.commandtoggle.internal.CommandToggleConfig;
import fr.elias.oessentials.commandtoggle.internal.CommandToggleService;

@ModuleApi("services")
public interface CommandToggleServices {
    CommandToggleConfig getCommandToggleConfig();
    CommandToggleService getCommandToggleService();
}
