package fr.elias.oessentials.daily.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface DailyServices {
    fr.elias.oessentials.daily.internal.DailyConfig getDailyConfig();
    fr.elias.oessentials.daily.internal.RewardsConfig getDailyRewardsConfig();
    fr.elias.oessentials.daily.internal.DailyService getDailyService();
    fr.elias.oessentials.daily.internal.DailyMongoStore getDailyStore();
}
