package fr.elias.oreoEssentials.holograms.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface HologramsServices {
    fr.elias.oreoEssentials.holograms.internal.OHolograms getEmbeddedOHolograms();
}
