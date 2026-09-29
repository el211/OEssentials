package fr.elias.oreoEssentials.shards.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface ShardingServices {
    fr.elias.oreoEssentials.shards.internal.OreoShardsModule getShardsModule();
}
