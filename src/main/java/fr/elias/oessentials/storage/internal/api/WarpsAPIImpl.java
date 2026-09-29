package fr.elias.oessentials.storage.internal.api;

import fr.elias.oessentials.api.IWarpsAPI;
import fr.elias.oessentials.storage.internal.warps.WarpService;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class WarpsAPIImpl implements IWarpsAPI {
    private final WarpService svc;
    public WarpsAPIImpl(WarpService svc) { this.svc = svc; }

    @Override public @NotNull Set<String> listWarps() { return svc.listWarps(); }
    @Override public @Nullable Location getWarp(@NotNull String name) { return svc.getWarp(name); }
    @Override public boolean setWarp(@NotNull String name, @NotNull Location loc) { return svc.setWarp(name, loc); }
    @Override public boolean delWarp(@NotNull String name) { return svc.delWarp(name); }
    @Override public boolean canUse(@NotNull Player p, @NotNull String name) { return svc.canUse(p, name); }
    @Override public boolean renameWarp(@NotNull String oldName, @NotNull String newName) { return svc.renameWarp(oldName, newName); }
}
