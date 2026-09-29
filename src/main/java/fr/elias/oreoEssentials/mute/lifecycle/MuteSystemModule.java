package fr.elias.oreoEssentials.mute.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.chat.internal.MuteListener;
import fr.elias.oreoEssentials.chat.internal.chatservices.MuteService;

@PluginModule(value = "mute-system", dependencies = {"daily"})
public final class MuteSystemModule extends ManagedModule implements MuteSystemServices {
    private MuteService muteService;

    @Override
    protected void start() {
        initMuteSystem();
    }

    @Override public MuteService getMuteService() { return muteService; }

    private void initMuteSystem() {
        this.muteService = new MuteService(plugin);
        plugin.getServer().getPluginManager().registerEvents(new MuteListener(muteService), plugin);
    }
}
