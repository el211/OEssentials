package fr.elias.oreoEssentials.jumppads.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "jump-pads", dependencies = {"configuration::services", "group-rtp"})
public final class JumpPadsModule extends ManagedModule implements JumpPadsServices {
    private fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsManager jumpPads;

    @Override
    protected void start() {
        initJumpPads();
    }

    @Override public fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsManager getJumpPads() { return jumpPads; }

    private void initJumpPads() {
        this.jumpPads = new fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsManager(plugin);
        plugin.getServer().getPluginManager().registerEvents(new fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsListener(this.jumpPads), plugin);
        var jumpCmd = new fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsCommand(this.jumpPads);
        services(ConfigurationServices.class).getCommands().registerLegacy("jumpad", jumpCmd, jumpCmd);
    }
}
