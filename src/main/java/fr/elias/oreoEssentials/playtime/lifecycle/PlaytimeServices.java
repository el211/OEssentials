package fr.elias.oreoEssentials.playtime.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PlaytimeServices {
    fr.elias.oreoEssentials.playtime.internal.PlaytimeRewardsService getPlaytimeRewards();
    fr.elias.oreoEssentials.playtime.internal.PlaytimeTracker getPlaytimeTracker();
}
