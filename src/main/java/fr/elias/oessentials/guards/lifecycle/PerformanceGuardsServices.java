package fr.elias.oessentials.guards.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.guards.internal.services.JoinFloodGuardService;

@ModuleApi("services")
public interface PerformanceGuardsServices {
    JoinFloodGuardService getJoinFloodGuardService();
}
