package fr.elias.oreoEssentials.player.internal.api;

import fr.elias.oreoEssentials.api.ITeleportAPI;
import fr.elias.oreoEssentials.player.internal.tp.service.TeleportService;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportAPIImpl implements ITeleportAPI {
    private final TeleportService svc;
    public TeleportAPIImpl(TeleportService svc) { this.svc = svc; }

    @Override public boolean request(@NotNull Player from, @NotNull Player to) { return svc.request(from, to); }
    @Override public boolean accept(@NotNull Player target) { return svc.accept(target); }
    @Override public boolean deny(@NotNull Player target) { return svc.deny(target); }
    @Override public void teleportSilently(@NotNull Player who, @NotNull Location to) { svc.teleportSilently(who, to); }
    @Override public void teleportSilently(@NotNull Player who, @NotNull Player target) { svc.teleportSilently(who, target); }
}
