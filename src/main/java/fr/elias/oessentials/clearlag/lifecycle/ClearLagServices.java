package fr.elias.oessentials.clearlag.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.clearlag.internal.ClearLagManager;

@ModuleApi("services")
public interface ClearLagServices {
    ClearLagManager getClearLag();
}
