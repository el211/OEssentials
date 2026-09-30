package fr.elias.oessentials.playervaults.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PlayerVaultsServices {
    fr.elias.oessentials.playervaults.internal.PlayerVaultsConfig getPlayerVaultsConfig();
    fr.elias.oessentials.playervaults.internal.PlayerVaultsService getPlayervaultsService();
}
