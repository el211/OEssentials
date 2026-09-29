package fr.elias.oessentials.kits.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface KitsServices {
    fr.elias.oessentials.kits.internal.KitsManager getKitsManager();
}
