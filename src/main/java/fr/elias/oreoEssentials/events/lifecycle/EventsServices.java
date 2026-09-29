package fr.elias.oreoEssentials.events.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface EventsServices {
    fr.elias.oreoEssentials.events.internal.EventConfig getEventConfig();
    fr.elias.oreoEssentials.events.internal.DeathMessageService getDeathMessages();
}
