package fr.elias.oreoEssentials.inventory.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.commandtoggle.lifecycle.CommandToggleServices;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.AuctionHouseModule;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands.AdminCommand;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands.AuctionHouseCommand;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands.ExpiredCommand;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands.SearchCommand;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands.SellCommand;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands.SoldCommand;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.hooks.AuctionPlaceholders;
import fr.elias.oreoEssentials.inventory.internal.sellgui.manager.SellGuiManager;
import fr.minuskube.inv.InventoryManager;
import java.io.File;
import org.bukkit.Bukkit;

@PluginModule(value = "inventories", dependencies = {"command-toggle::services", "configuration::services", "mail"})
public final class InventoriesModule extends ManagedModule implements InventoriesServices {
    private AuctionHouseModule auctionHouse;
    private fr.elias.oreoEssentials.inventory.internal.orders.OrdersModule ordersModule;
    private InventoryManager invManager;
    private SellGuiManager sellGuiManager;

    @Override
    protected void start() {
        cleanup("auctionHouse", () -> { if (auctionHouse != null) auctionHouse.stop(); });
        cleanup("ordersModule", () -> { if (ordersModule != null) ordersModule.stop(); });
        initInventoryManagers();
    }

    @Override public AuctionHouseModule getAuctionHouse() { return auctionHouse; }
    @Override public fr.elias.oreoEssentials.inventory.internal.orders.OrdersModule getOrdersModule() { return ordersModule; }
    @Override public InventoryManager getInvManager() { return invManager; }
    @Override public SellGuiManager getSellGuiManager() { return sellGuiManager; }

    private void initInventoryManagers() {
        this.invManager    = new InventoryManager(plugin);
        this.invManager.init();
        this.sellGuiManager = new SellGuiManager(plugin, this.invManager);

        this.auctionHouse = new AuctionHouseModule(plugin);
        if (auctionHouse.enabled()) {
            services(ConfigurationServices.class).getCommands().registerLegacy("ah",        new AuctionHouseCommand(auctionHouse));
            services(ConfigurationServices.class).getCommands().registerLegacy("ahs",       new SellCommand(auctionHouse));
            services(ConfigurationServices.class).getCommands().registerLegacy("ahsearch",  new SearchCommand(auctionHouse));
            services(ConfigurationServices.class).getCommands().registerLegacy("ahexpired", new ExpiredCommand(auctionHouse));
            services(ConfigurationServices.class).getCommands().registerLegacy("ahsold",    new SoldCommand(auctionHouse));
            services(ConfigurationServices.class).getCommands().registerLegacy("ahadmin",   new AdminCommand(auctionHouse));
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new AuctionPlaceholders(auctionHouse).register();
        }

        if (services(CommandToggleServices.class).getCommandToggleConfig() != null) {
            services(CommandToggleServices.class).getCommandToggleConfig().registerModuleCallback("auctionhouse", () -> {
                boolean shouldBeEnabled = services(CommandToggleServices.class).getCommandToggleConfig().isCommandEnabled("auctionhouse");
                File ahConfig = new File(plugin.getDataFolder(), "auctionhouse/config.yml");
                org.bukkit.configuration.file.YamlConfiguration ahCfg = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(ahConfig);
                ahCfg.set("enabled", shouldBeEnabled);
                try { ahCfg.save(ahConfig); } catch (Exception e) { plugin.getLogger().warning("[CommandToggle] Failed to save AH config: " + e.getMessage()); }
                auctionHouse.reload();
                plugin.getLogger().info("[CommandToggle] AuctionHouse " + (shouldBeEnabled ? "enabled" : "disabled") + " via toggle.");
            });
            plugin.getLogger().info("[CommandToggle] AuctionHouse module callback registered.");
        }

        // Orders / Market module
        try {
            this.ordersModule = new fr.elias.oreoEssentials.inventory.internal.orders.OrdersModule(plugin);
            if (ordersModule.enabled()) {
                plugin.getLogger().info("[Orders] Market module initialised.");
            }
        } catch (Throwable t) {
            plugin.getLogger().warning("[Orders] Failed to initialise: " + t.getMessage());
            this.ordersModule = null;
        }
    }
}
