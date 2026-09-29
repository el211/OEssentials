package fr.elias.oreoEssentials.ignore.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface IgnoreServices {
    fr.elias.oreoEssentials.ignore.internal.IgnoreService getIgnoreService();
}
