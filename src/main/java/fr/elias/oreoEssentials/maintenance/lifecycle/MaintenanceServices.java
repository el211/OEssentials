package fr.elias.oreoEssentials.maintenance.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface MaintenanceServices {
    fr.elias.oreoEssentials.maintenance.internal.MaintenanceConfig getMaintenanceConfig();
    fr.elias.oreoEssentials.maintenance.internal.MaintenanceService getMaintenanceService();
}
