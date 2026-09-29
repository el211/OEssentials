package fr.elias.oreoEssentials.economy.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.economy.internal.persistence.database.PlayerEconomyDatabase;
import fr.elias.oreoEssentials.economy.internal.EconomyBootstrap;
import fr.elias.oreoEssentials.economy.internal.offline.OfflinePlayerCache;
import net.milkbowl.vault.economy.Economy;

@ModuleApi("services")
public interface EconomyServices {
    EconomyBootstrap getEcoBootstrap();
    PlayerEconomyDatabase getDatabase();
    OfflinePlayerCache getOfflinePlayerCache();
    Economy getVaultEconomy();
}
