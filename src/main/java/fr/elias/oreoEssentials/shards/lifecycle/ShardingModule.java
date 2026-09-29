package fr.elias.oreoEssentials.shards.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "sharding", dependencies = {"configuration::services", "holograms"})
public final class ShardingModule extends ManagedModule implements ShardingServices {
    private fr.elias.oreoEssentials.shards.internal.OreoShardsModule shardsModule;

    @Override
    protected void start() {
        cleanup("shardsModule", () -> { if (shardsModule != null) shardsModule.disable(); });
        initSharding();
    }

    @Override public fr.elias.oreoEssentials.shards.internal.OreoShardsModule getShardsModule() { return shardsModule; }

    private void initSharding() {
        if (services(ConfigurationServices.class).getSettingsConfig().worldShardingEnabled()) {
            try {
                this.shardsModule = new fr.elias.oreoEssentials.shards.internal.OreoShardsModule(plugin);
                this.shardsModule.enable();
                plugin.getLogger().info("[Sharding] World sharding enabled.");
            } catch (Throwable t) {
                this.shardsModule = null;
                plugin.getLogger().warning("[Sharding] Failed to initialize: " + t.getMessage());
            }
        } else {
            this.shardsModule = null;
            plugin.getLogger().info("[Sharding] Disabled by settings.yml.");
        }
    }
}
