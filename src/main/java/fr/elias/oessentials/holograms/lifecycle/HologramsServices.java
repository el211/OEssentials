package fr.elias.oessentials.holograms.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface HologramsServices {
    fr.elias.oessentials.holograms.internal.OHolograms getEmbeddedOHolograms();
}
