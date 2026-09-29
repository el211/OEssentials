package fr.elias.oreoEssentials.portals.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PortalsServices {
    fr.elias.oreoEssentials.portals.internal.PortalsManager getPortals();
}
