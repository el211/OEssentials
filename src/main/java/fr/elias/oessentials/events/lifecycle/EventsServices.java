package fr.elias.oessentials.events.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface EventsServices {
    fr.elias.oessentials.events.internal.EventConfig getEventConfig();
    fr.elias.oessentials.events.internal.DeathMessageService getDeathMessages();
}
