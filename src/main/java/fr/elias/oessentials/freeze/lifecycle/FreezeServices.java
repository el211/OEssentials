package fr.elias.oessentials.freeze.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.freeze.internal.freeze.FreezeManager;

@ModuleApi("services")
public interface FreezeServices {
    FreezeManager getFreezeManager();
}
