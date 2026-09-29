package fr.elias.oreoEssentials.playervaults.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PlayerVaultsServices {
    fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsConfig getPlayerVaultsConfig();
    fr.elias.oreoEssentials.playervaults.internal.PlayerVaultsService getPlayervaultsService();
}
