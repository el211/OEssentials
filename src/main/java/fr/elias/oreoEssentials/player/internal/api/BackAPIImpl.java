package fr.elias.oreoEssentials.player.internal.api;

import fr.elias.oreoEssentials.api.IBackAPI;
import fr.elias.oreoEssentials.player.internal.back.service.BackService;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BackAPIImpl implements IBackAPI {
    private final BackService svc;
    public BackAPIImpl(BackService svc) { this.svc = svc; }

    @Override public @Nullable Location getLastLocal(@NotNull UUID playerId) { return svc.getLastLocal(playerId); }
    @Override public void setLast(@NotNull UUID playerId, @NotNull Location location) { svc.setLast(playerId, location); }
}
