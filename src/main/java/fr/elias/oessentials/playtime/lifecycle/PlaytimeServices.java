package fr.elias.oessentials.playtime.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PlaytimeServices {
    fr.elias.oessentials.playtime.internal.PlaytimeRewardsService getPlaytimeRewards();
    fr.elias.oessentials.playtime.internal.PlaytimeTracker getPlaytimeTracker();
}
