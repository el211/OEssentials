package fr.elias.oessentials.currency.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.currency.internal.CurrencyConfig;
import fr.elias.oessentials.currency.internal.CurrencyService;

@ModuleApi("services")
public interface CurrencySystemServices {
    CurrencyService getCurrencyService();
    CurrencyConfig getCurrencyConfig();
}
