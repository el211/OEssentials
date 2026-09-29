package fr.elias.oreoEssentials.freeze.internal.api;

import fr.elias.oreoEssentials.api.IFreezeAPI;
import fr.elias.oreoEssentials.configuration.internal.services.FreezeService;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class FreezeAPIImpl implements IFreezeAPI {
    private final FreezeService svc;
    public FreezeAPIImpl(FreezeService svc) { this.svc = svc; }

    @Override public boolean isFrozen(@NotNull UUID playerId) { return svc.isFrozen(playerId); }
    @Override public void setFrozen(@NotNull UUID playerId, boolean frozen) { svc.set(playerId, frozen); }
}
