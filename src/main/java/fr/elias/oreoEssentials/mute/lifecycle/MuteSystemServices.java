package fr.elias.oreoEssentials.mute.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.chat.internal.chatservices.MuteService;

@ModuleApi("services")
public interface MuteSystemServices {
    MuteService getMuteService();
}
