package fr.elias.oreoEssentials.clearlag.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.clearlag.internal.ClearLagManager;

@ModuleApi("services")
public interface ClearLagServices {
    ClearLagManager getClearLag();
}
