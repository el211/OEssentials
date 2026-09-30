package fr.elias.oessentials.warnings.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface WarningsServices {
    fr.elias.oessentials.warnings.internal.WarnService getWarnService();
}
