package fr.elias.oessentials.economy.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.economy.internal.persistence.database.PlayerEconomyDatabase;
import fr.elias.oessentials.economy.internal.EconomyBootstrap;
import fr.elias.oessentials.economy.internal.offline.OfflinePlayerCache;
import net.milkbowl.vault.economy.Economy;

@ModuleApi("services")
public interface EconomyServices {
    EconomyBootstrap getEcoBootstrap();
    PlayerEconomyDatabase getDatabase();
    OfflinePlayerCache getOfflinePlayerCache();
    Economy getVaultEconomy();
}
