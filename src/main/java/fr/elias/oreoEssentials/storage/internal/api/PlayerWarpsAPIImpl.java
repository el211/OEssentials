package fr.elias.oreoEssentials.storage.internal.api;

import fr.elias.oreoEssentials.api.IPlayerWarpsAPI;
import fr.elias.oreoEssentials.storage.internal.playerwarp.PlayerWarp;
import fr.elias.oreoEssentials.storage.internal.playerwarp.PlayerWarpService;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class PlayerWarpsAPIImpl implements IPlayerWarpsAPI {
    private final PlayerWarpService svc;
    public PlayerWarpsAPIImpl(PlayerWarpService svc) { this.svc = svc; }

    @Override public @Nullable PlayerWarp createWarp(@NotNull Player owner, @NotNull String name, @NotNull Location loc) { return svc.createWarp(owner, name, loc); }
    @Override public boolean deleteWarp(@NotNull PlayerWarp warp) { return svc.deleteWarp(warp); }
    @Override public @Nullable PlayerWarp findByOwnerAndName(@NotNull UUID ownerId, @NotNull String name) { return svc.findByOwnerAndName(ownerId, name); }
    @Override public @NotNull List<PlayerWarp> listByOwner(@NotNull UUID ownerId) { return svc.listByOwner(ownerId); }
    @Override public @NotNull List<PlayerWarp> listAll() { return svc.listAll(); }
    @Override public int getWarpCount(@NotNull Player owner) { return svc.getWarpCount(owner); }
    @Override public boolean canUse(@NotNull Player player, @NotNull PlayerWarp warp) { return svc.canUse(player, warp); }
    @Override public boolean teleportTo(@NotNull Player player, @NotNull UUID ownerId, @NotNull String warpName) { return svc.teleportToPlayerWarp(player, ownerId, warpName); }
}
