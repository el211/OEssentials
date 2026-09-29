package fr.elias.oessentials.player.internal.api;

import fr.elias.oessentials.api.IDeathBackAPI;
import fr.elias.oessentials.player.internal.deathback.DeathBackService;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DeathBackAPIImpl implements IDeathBackAPI {
    private final DeathBackService svc;
    public DeathBackAPIImpl(DeathBackService svc) { this.svc = svc; }

    @Override public @Nullable Location getLastDeath(@NotNull UUID playerId) { return svc.getLastDeath(playerId); }
    @Override public void setLastDeath(@NotNull UUID playerId, @NotNull Location location) { svc.setLastDeath(playerId, location); }
    @Override public void clear(@NotNull UUID playerId) { svc.clear(playerId); }
}
