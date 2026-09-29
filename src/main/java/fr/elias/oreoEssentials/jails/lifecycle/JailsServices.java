package fr.elias.oreoEssentials.jails.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface JailsServices {
    fr.elias.oreoEssentials.jails.internal.jail.JailService getJailService();
}
