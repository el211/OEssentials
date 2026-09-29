package fr.elias.oreoEssentials.integrations.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.integrations.internal.placeholders.PlaceholderAPIHook;
import fr.elias.oreoEssentials.currency.internal.placeholders.CurrencyPlaceholderExpansion;
import org.bstats.bukkit.Metrics;

@ModuleApi("services")
public interface IntegrationsServices {
    Metrics getMetrics();
    PlaceholderAPIHook getPlaceholderHook();
    CurrencyPlaceholderExpansion getCurrencyPlaceholders();
}
