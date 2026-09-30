package fr.elias.oessentials.maintenance.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface MaintenanceServices {
    fr.elias.oessentials.maintenance.internal.MaintenanceConfig getMaintenanceConfig();
    fr.elias.oessentials.maintenance.internal.MaintenanceService getMaintenanceService();
}
