package fr.elias.oreoEssentials.kits.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface KitsServices {
    fr.elias.oreoEssentials.kits.internal.KitsManager getKitsManager();
}
