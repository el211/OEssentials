package fr.elias.oreoEssentials.configuration.internal.api;

import fr.elias.oreoEssentials.api.ITempFlyAPI;
import fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TempFlyAPIImpl implements ITempFlyAPI {
    private final TempFlyService svc;
    public TempFlyAPIImpl(TempFlyService svc) { this.svc = svc; }

    @Override public void enableFly(@NotNull Player player) { svc.enableFly(player); }
    @Override public void disableFly(@NotNull Player player) { svc.disableFly(player); }
    @Override public int getTimeRemaining(@NotNull Player player) { return svc.getTimeRemaining(player); }
}
