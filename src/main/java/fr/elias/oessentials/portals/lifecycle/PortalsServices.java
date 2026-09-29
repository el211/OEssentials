package fr.elias.oessentials.portals.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PortalsServices {
    fr.elias.oessentials.portals.internal.PortalsManager getPortals();
}
