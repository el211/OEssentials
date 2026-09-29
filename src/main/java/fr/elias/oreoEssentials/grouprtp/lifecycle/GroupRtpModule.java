package fr.elias.oreoEssentials.grouprtp.lifecycle;

import dev.oreo.modulith.core.ModuleListener;
import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.messaging.lifecycle.MessagingReady;

@PluginModule(value = "group-rtp", dependencies = {"messaging::events", "portals"})
public final class GroupRtpModule extends ManagedModule implements GroupRtpServices {
    private fr.elias.oreoEssentials.grouprtp.internal.GroupRtpModule groupRtpModule;

    @Override
    protected void start() {
        initGroupRtp();
    }

    @Override public fr.elias.oreoEssentials.grouprtp.internal.GroupRtpModule getGroupRtpModule() { return groupRtpModule; }

    private void initGroupRtp() {
        try {
            this.groupRtpModule = new fr.elias.oreoEssentials.grouprtp.internal.GroupRtpModule(plugin);
            this.groupRtpModule.init();
        } catch (Throwable t) {
            plugin.getLogger().severe("[GroupRTP] Failed to initialize: " + t.getMessage());
        }
    }

    @ModuleListener
    public void onMessagingReady(MessagingReady event) {
        if (!isActive() || groupRtpModule == null) return;
        new fr.elias.oreoEssentials.grouprtp.internal.rabbit.GroupRtpCrossServerBroker(plugin, event.packets());
        plugin.getLogger().info("[GroupRTP] Cross-server broker ready.");
    }

}
