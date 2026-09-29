package fr.elias.oreoEssentials.proxy.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.proxy.internal.transport.ProxyMessenger;

@ModuleApi("services")
public interface ProxyMessagingServices {
    ProxyMessenger getProxyMessenger();
    fr.elias.oreoEssentials.commandcontrol.internal.aliases.AliasService getAliasService();
}
