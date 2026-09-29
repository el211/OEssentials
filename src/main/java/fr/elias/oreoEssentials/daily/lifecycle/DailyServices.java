package fr.elias.oreoEssentials.daily.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface DailyServices {
    fr.elias.oreoEssentials.daily.internal.DailyConfig getDailyConfig();
    fr.elias.oreoEssentials.daily.internal.RewardsConfig getDailyRewardsConfig();
    fr.elias.oreoEssentials.daily.internal.DailyService getDailyService();
    fr.elias.oreoEssentials.daily.internal.DailyMongoStore getDailyStore();
}
