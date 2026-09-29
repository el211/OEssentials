package fr.elias.oreoEssentials.playervaults.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;

@PluginModule(value = "player-vaults", dependencies = {"jump-pads"})
public final class PlayerVaultsModule extends ManagedModule implements PlayerVaultsServices {
    private fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsConfig playerVaultsConfig;
    private fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsService playervaultsService;

    @Override
    protected void start() {
        cleanup("playervaultsService", () -> { if (playervaultsService != null) playervaultsService.stop(); });
        initPlayerVaults();
    }

    @Override public fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsConfig getPlayerVaultsConfig() { return playerVaultsConfig; }
    @Override public fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsService getPlayervaultsService() { return playervaultsService; }

    private void initPlayerVaults() {
        this.playerVaultsConfig  = new fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsConfig(plugin);
        this.playervaultsService = new fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsService(plugin);
        if (this.playervaultsService.enabled()) { plugin.getLogger().info("[Vaults] PlayerVaults enabled."); }
        else { plugin.getLogger().info("[Vaults] PlayerVaults disabled by config or storage unavailable."); }
    }
}
