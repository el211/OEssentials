package fr.elias.oreoEssentials.shards.internal.api;

import fr.elias.oreoEssentials.api.IShardsAPI;
import fr.elias.oreoEssentials.shards.internal.OreoShardsModule;
import fr.elias.oreoEssentials.shards.internal.ShardManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShardsAPIImpl implements IShardsAPI {
    private final OreoShardsModule module;

    public ShardsAPIImpl(OreoShardsModule module) { this.module = module; }

    @Override public boolean isEnabled() { return module.isEnabled(); }

    @Override public @Nullable String getCurrentShardId() {
        ShardManager mgr = module.getShardManager();
        return mgr == null ? null : mgr.getCurrentShardId();
    }

    @Override public @Nullable String getTargetShardAtBorder(@NotNull String worldName, double x, double z, int buffer) {
        ShardManager mgr = module.getShardManager();
        return mgr == null ? null : mgr.getTargetShardAtBorder(worldName, x, z, buffer);
    }

    @Override public void transferPlayerToShard(@NotNull Player player, @NotNull String targetShard) {
        ShardManager mgr = module.getShardManager();
        if (mgr != null) mgr.transferPlayerToShard(player, targetShard);
    }

    @Override public boolean isInSafeZone(@NotNull String worldName, double x, double z) {
        ShardManager mgr = module.getShardManager();
        return mgr != null && mgr.isInSafeZone(worldName, x, z);
    }
}
