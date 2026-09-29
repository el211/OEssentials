package fr.elias.oreoEssentials.proxy.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.proxy.internal.transport.ProxyMessenger;

@PluginModule(value = "proxy-messaging", dependencies = {"kits"})
public final class ProxyMessagingModule extends ManagedModule implements ProxyMessagingServices {
    private ProxyMessenger proxyMessenger;
    private fr.elias.oreoEssentials.commandcontrol.internal.aliases.AliasService aliasService;

    @Override
    protected void start() {
        cleanup("aliasService", () -> { if (aliasService != null) aliasService.shutdown(); });
        initProxyMessaging();
    }

    @Override public ProxyMessenger getProxyMessenger() { return proxyMessenger; }
    @Override public fr.elias.oreoEssentials.commandcontrol.internal.aliases.AliasService getAliasService() { return aliasService; }

    private void initProxyMessaging() {
        this.aliasService = new fr.elias.oreoEssentials.commandcontrol.internal.aliases.AliasService(plugin);
        this.aliasService.load();
        this.aliasService.applyRuntimeRegistration();

        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "bungeecord:main");
        this.proxyMessenger = new ProxyMessenger(plugin);
        plugin.getLogger().info("[BOOT] Registered proxy plugin messaging channels.");
    }
}
