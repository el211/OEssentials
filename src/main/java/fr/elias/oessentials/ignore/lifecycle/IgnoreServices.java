package fr.elias.oessentials.ignore.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface IgnoreServices {
    fr.elias.oessentials.ignore.internal.IgnoreService getIgnoreService();
}
