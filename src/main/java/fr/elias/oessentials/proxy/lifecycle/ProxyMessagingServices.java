package fr.elias.oessentials.proxy.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.proxy.internal.transport.ProxyMessenger;

@ModuleApi("services")
public interface ProxyMessagingServices {
    ProxyMessenger getProxyMessenger();
    fr.elias.oessentials.commandcontrol.internal.aliases.AliasService getAliasService();
}
