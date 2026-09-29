package fr.elias.oreoEssentials.freeze.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.freeze.internal.freeze.FreezeManager;

@ModuleApi("services")
public interface FreezeServices {
    FreezeManager getFreezeManager();
}
