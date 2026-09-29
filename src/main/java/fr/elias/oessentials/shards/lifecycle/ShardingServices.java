package fr.elias.oessentials.shards.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface ShardingServices {
    fr.elias.oessentials.shards.internal.OreoShardsModule getShardsModule();
}
