package fr.elias.oessentials.jails.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface JailsServices {
    fr.elias.oessentials.jails.internal.jail.JailService getJailService();
}
