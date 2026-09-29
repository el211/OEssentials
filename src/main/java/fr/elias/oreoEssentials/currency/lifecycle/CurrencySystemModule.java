package fr.elias.oreoEssentials.currency.lifecycle;

import dev.oreo.modulith.core.ModuleListener;
import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.messaging.lifecycle.MessagingReady;
import fr.elias.oreoEssentials.storage.lifecycle.StorageServices;
import fr.elias.oreoEssentials.currency.internal.CurrencyConfig;
import fr.elias.oreoEssentials.currency.internal.CurrencyService;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencyBalanceCommand;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencyBalanceTabCompleter;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencyCommand;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencyCommandTabCompleter;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencySendCommand;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencySendTabCompleter;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencyTopCommand;
import fr.elias.oreoEssentials.currency.internal.commands.CurrencyTopTabCompleter;
import fr.elias.oreoEssentials.currency.internal.storage.CurrencyStorage;
import fr.elias.oreoEssentials.currency.internal.storage.JsonCurrencyStorage;
import fr.elias.oreoEssentials.currency.internal.storage.MongoCurrencyStorage;
import org.bukkit.Bukkit;

@PluginModule(value = "currency-system", dependencies = {"messaging::events", "configuration::services", "messaging::services", "storage::services"})
public final class CurrencySystemModule extends ManagedModule implements CurrencySystemServices {
    private CurrencyService currencyService;
    private CurrencyConfig currencyConfig;

    @Override
    protected void start() {
        initCurrencySystem();
    }

    @Override public CurrencyService getCurrencyService() { return currencyService; }
    @Override public CurrencyConfig getCurrencyConfig() { return currencyConfig; }

    // -------------------------------------------------------------------------
    // Currency
    // -------------------------------------------------------------------------

    private void initCurrencySystem() {
        if (!services(ConfigurationServices.class).getSettingsConfig().currencyEnabled()) { plugin.getLogger().info("[Currency] Disabled by settings.yml"); return; }
        try {
            this.currencyConfig = new CurrencyConfig(plugin);
            final CurrencyStorage currencyStorage;
            if (currencyConfig.useMongoStorage() && services(StorageServices.class).getHomesMongoClient() != null) {
                String dbName = plugin.getConfig().getString("storage.mongo.database", "oreo");
                String prefix = plugin.getConfig().getString("storage.mongo.collectionPrefix", "oreo_");
                currencyStorage = new MongoCurrencyStorage(services(StorageServices.class).getHomesMongoClient(), dbName, prefix);
                plugin.getLogger().info("[Currency] Using MongoDB storage");
            } else {
                currencyStorage = new JsonCurrencyStorage(plugin);
                plugin.getLogger().info("[Currency] Using JSON storage");
            }
            this.currencyService = new CurrencyService(plugin, currencyStorage, currencyConfig);
            Bukkit.getServicesManager().register(
                    fr.elias.oreoEssentials.api.OreoEssentialsAPI.class,
                    new fr.elias.oreoEssentials.integrations.internal.api.OreoEssentialsAPIImpl(plugin),
                    plugin,
                    org.bukkit.plugin.ServicePriority.Normal);
            plugin.getLogger().info("[Currency] Registered OreoEssentialsAPI via ServicesManager");
            services(ConfigurationServices.class).getCommands().register(new CurrencyCommand(plugin)).register(new CurrencyBalanceCommand(plugin)).register(new CurrencySendCommand(plugin)).register(new CurrencyTopCommand(plugin));
            services(ConfigurationServices.class).getCommands().rewireTab("currency",        new CurrencyCommandTabCompleter(plugin));
            services(ConfigurationServices.class).getCommands().rewireTab("currencybalance", new CurrencyBalanceTabCompleter(plugin));
            services(ConfigurationServices.class).getCommands().rewireTab("currencysend",    new CurrencySendTabCompleter(plugin));
            services(ConfigurationServices.class).getCommands().rewireTab("currencytop",     new CurrencyTopTabCompleter(plugin));
            plugin.getLogger().info("[Currency] Enabled");
        } catch (Throwable t) {
            plugin.getLogger().severe("[Currency] Failed to initialize: " + t.getMessage());
            t.printStackTrace();
        }
    }

    @ModuleListener
    public void onMessagingReady(MessagingReady event) {
        if (!isActive() || currencyService == null || !currencyConfig.isCrossServerEnabled()) return;
        event.packets().subscribe(fr.elias.oreoEssentials.currency.internal.rabbitmq.CurrencySyncPacket.class,
                (channel, packet) -> currencyService.handleCurrencySync(packet));
        plugin.getLogger().info("[Currency] Cross-server sync subscribed.");
    }

}
