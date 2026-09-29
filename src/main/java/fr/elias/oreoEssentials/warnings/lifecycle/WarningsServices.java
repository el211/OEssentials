package fr.elias.oreoEssentials.warnings.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface WarningsServices {
    fr.elias.oreoEssentials.warnings.internal.WarnService getWarnService();
}
