package fr.elias.oreoEssentials.guards.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.guards.internal.services.JoinFloodGuardService;

@ModuleApi("services")
public interface PerformanceGuardsServices {
    JoinFloodGuardService getJoinFloodGuardService();
}
