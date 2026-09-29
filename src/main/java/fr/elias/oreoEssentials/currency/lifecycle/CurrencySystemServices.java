package fr.elias.oreoEssentials.currency.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.currency.internal.CurrencyConfig;
import fr.elias.oreoEssentials.currency.internal.CurrencyService;

@ModuleApi("services")
public interface CurrencySystemServices {
    CurrencyService getCurrencyService();
    CurrencyConfig getCurrencyConfig();
}
