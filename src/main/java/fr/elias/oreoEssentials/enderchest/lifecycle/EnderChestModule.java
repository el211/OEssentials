package fr.elias.oreoEssentials.enderchest.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.storage.lifecycle.StorageServices;
import org.bukkit.Bukkit;

@PluginModule(value = "ender-chest", dependencies = {"chat", "configuration::services", "storage::services"})
public final class EnderChestModule extends ManagedModule implements EnderChestServices {
    private fr.elias.oreoEssentials.enderchest.internal.EnderChestConfig ecConfig;
    private fr.elias.oreoEssentials.enderchest.internal.EnderChestService ecService;

    @Override
    protected void start() {
        initEnderChest();
    }

    @Override public fr.elias.oreoEssentials.enderchest.internal.EnderChestConfig getEcConfig() { return ecConfig; }
    @Override public fr.elias.oreoEssentials.enderchest.internal.EnderChestService getEcService() { return ecService; }

    private void initEnderChest() {
        this.ecConfig = new fr.elias.oreoEssentials.enderchest.internal.EnderChestConfig(plugin);
        final boolean crossServerEc = services(ConfigurationServices.class).getSettingsConfig().featureOption("cross-server", "enderchest", true);
        final boolean mongoStorage  = "mongodb".equalsIgnoreCase(plugin.getConfig().getString("essentials.storage", "yaml"));

        fr.elias.oreoEssentials.enderchest.internal.EnderChestStorage ecStorage;
        if (mongoStorage && crossServerEc && services(StorageServices.class).getHomesMongoClient() != null) {
            String dbName = plugin.getConfig().getString("storage.mongo.database", "oreo");
            String prefix = plugin.getConfig().getString("storage.mongo.collectionPrefix", "oreo_");
            ecStorage = new fr.elias.oreoEssentials.enderchest.internal.MongoEnderChestStorage(services(StorageServices.class).getHomesMongoClient(), dbName, prefix, plugin.getLogger());
            plugin.getLogger().info("[EC] Using MongoDB cross-server ender chest storage.");
        } else {
            ecStorage = new fr.elias.oreoEssentials.enderchest.internal.YamlEnderChestStorage(plugin);
            plugin.getLogger().info("[EC] Using local YAML ender chest storage.");
        }

        this.ecService = new fr.elias.oreoEssentials.enderchest.internal.EnderChestService(plugin, this.ecConfig, ecStorage);
        Bukkit.getServicesManager().register(fr.elias.oreoEssentials.enderchest.internal.EnderChestService.class, this.ecService, plugin, org.bukkit.plugin.ServicePriority.Normal);
        plugin.getServer().getPluginManager().registerEvents(new fr.elias.oreoEssentials.enderchest.internal.EnderChestListener(plugin, ecService, crossServerEc), plugin);
    }
}
