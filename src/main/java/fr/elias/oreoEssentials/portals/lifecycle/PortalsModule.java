package fr.elias.oreoEssentials.portals.lifecycle;

import dev.oreo.modulith.core.ModuleListener;
import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.messaging.lifecycle.MessagingReady;
import fr.elias.oreoEssentials.messaging.lifecycle.MessagingServices;

@PluginModule(value = "portals", dependencies = {"messaging::events", "configuration::services", "jails", "messaging::services"})
public final class PortalsModule extends ManagedModule implements PortalsServices {
    private fr.elias.oreoEssentials.portals.internal.PortalsManager portals;

    @Override
    protected void start() {
        initPortals();
    }

    @Override public fr.elias.oreoEssentials.portals.internal.PortalsManager getPortals() { return portals; }

    private void initPortals() {
        this.portals = new fr.elias.oreoEssentials.portals.internal.PortalsManager(plugin);

        var wandListener = new fr.elias.oreoEssentials.portals.internal.PortalWandListener(
                this.portals, this.portals.getConfig());
        plugin.getServer().getPluginManager().registerEvents(wandListener, plugin);
        plugin.getServer().getPluginManager().registerEvents(
                new fr.elias.oreoEssentials.portals.internal.PortalsListener(this.portals, wandListener), plugin);

        // Register BungeeCord outgoing channel for cross-server connects
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");

        // Wire up cross-server support if RabbitMQ is available
        if (services(MessagingServices.class).getPacketManager() != null && services(MessagingServices.class).getPacketManager().isInitialized()) {
            this.portals.initCrossServer(services(MessagingServices.class).getPacketManager());
            plugin.getLogger().info("[Portals] Cross-server support enabled.");
        } else {
            plugin.getLogger().info("[Portals] RabbitMQ unavailable — cross-server portals disabled.");
        }

        var portalCmd = new fr.elias.oreoEssentials.portals.internal.PortalsCommand(this.portals, wandListener);
        services(ConfigurationServices.class).getCommands().registerLegacy("portal", portalCmd, portalCmd);
    }

    @ModuleListener
    public void onMessagingReady(MessagingReady event) {
        if (!isActive() || portals == null) return;
        portals.initCrossServer(event.packets());
        plugin.getLogger().info("[Portals] Cross-server support enabled.");
    }

}
