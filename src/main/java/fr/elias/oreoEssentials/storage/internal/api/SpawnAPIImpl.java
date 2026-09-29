package fr.elias.oreoEssentials.storage.internal.api;

import fr.elias.oreoEssentials.api.ISpawnAPI;
import fr.elias.oreoEssentials.storage.internal.spawn.SpawnService;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpawnAPIImpl implements ISpawnAPI {
    private final SpawnService svc;
    public SpawnAPIImpl(SpawnService svc) { this.svc = svc; }

    @Override public @Nullable Location getSpawn() { return svc.getSpawn(); }
    @Override public void setSpawn(@NotNull Location l) { svc.setSpawn(l); }
    @Override public @Nullable Location getLocalSpawn() { return svc.getLocalSpawn(); }
    @Override public void setLocalSpawn(@NotNull Location l) { svc.setLocalSpawn(l); }
    @Override public @Nullable Location getGlobalSpawn() { return svc.getGlobalSpawn(); }
    @Override public void setGlobalSpawn(@NotNull Location l) { svc.setGlobalSpawn(l); }
    @Override public @Nullable Location getFirstJoinSpawn() { return svc.getFirstJoinSpawn(); }
    @Override public void setFirstJoinSpawn(@NotNull Location l) { svc.setFirstJoinSpawn(l); }
}
