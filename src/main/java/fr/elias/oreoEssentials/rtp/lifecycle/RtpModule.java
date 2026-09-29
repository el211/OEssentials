package fr.elias.oreoEssentials.rtp.lifecycle;

import dev.oreo.modulith.core.ModuleListener;
import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.BootstrapSupport;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.messaging.lifecycle.MessagingReady;
import fr.elias.oreoEssentials.rtp.internal.RtpCommand;
import fr.elias.oreoEssentials.rtp.internal.RtpConfig;
import fr.elias.oreoEssentials.rtp.internal.RtpPendingService;
import fr.elias.oreoEssentials.rtp.internal.listeners.RtpJoinListener;

@PluginModule(value = "rtp", dependencies = {"messaging::events", "configuration::services", "messaging::services", "player-vaults"})
public final class RtpModule extends ManagedModule implements RtpServices {
    private fr.elias.oreoEssentials.rtp.internal.RtpCrossServerBridge rtpBridge;
    @Override public fr.elias.oreoEssentials.rtp.internal.RtpCrossServerBridge getRtpBridge() { return rtpBridge; }

    private RtpPendingService rtpPendingService;
    private RtpConfig rtpConfig;
    private final java.util.Map<java.util.UUID, Long> rtpCooldownCache = new java.util.concurrent.ConcurrentHashMap<>();

    @Override
    protected void start() {
        initRtp();
    }

    @Override public RtpPendingService getRtpPendingService() { return rtpPendingService; }
    @Override public RtpConfig getRtpConfig() { return rtpConfig; }
    @Override public java.util.Map<java.util.UUID, Long> getRtpCooldownCache() { return rtpCooldownCache; }

    private void initRtp() {
        this.rtpPendingService = new RtpPendingService();
        this.rtpConfig         = new RtpConfig(plugin);
        plugin.getServer().getPluginManager().registerEvents(new RtpJoinListener(plugin), plugin);

        if (!services(ConfigurationServices.class).getSettingsConfig().rtpEnabled()) {
            BootstrapSupport.unregisterCommandHard(plugin, "rtp"); BootstrapSupport.unregisterCommandHard(plugin, "wild");
            plugin.getLogger().info("[RTP] Disabled by settings.yml.");
        } else if (!this.rtpConfig.isEnabled()) {
            BootstrapSupport.unregisterCommandHard(plugin, "rtp"); BootstrapSupport.unregisterCommandHard(plugin, "wild");
            plugin.getLogger().info("[RTP] Disabled by rtp.yml.");
        } else {
            services(ConfigurationServices.class).getCommands().register(new RtpCommand());
            plugin.getLogger().info("[RTP] Enabled.");
        }

        // Bridge requires packetManager which is created async in initRabbitMQ().
        // It is initialized in initBrokers() once the connection is established.

    }

    @ModuleListener
    public void onMessagingReady(MessagingReady event) {
        if (!isActive() || rtpConfig == null || !rtpConfig.isCrossServerEnabled()) return;
        rtpBridge = new fr.elias.oreoEssentials.rtp.internal.RtpCrossServerBridge(
                plugin, event.packets(), services(ConfigurationServices.class).getConfigService().serverName());
        plugin.getLogger().info("[RTP] Cross-server RTP bridge ready.");
    }

}
