package fr.elias.oessentials.player.internal.api;

import fr.elias.oessentials.api.IVanishAPI;
import fr.elias.oessentials.player.internal.services.VanishService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class VanishAPIImpl implements IVanishAPI {
    private final VanishService svc;
    public VanishAPIImpl(VanishService svc) { this.svc = svc; }

    @Override public boolean isVanished(@NotNull Player player) { return svc.isVanished(player); }
    @Override public boolean isVanished(@NotNull UUID playerId) { return svc.isVanished(playerId); }
    @Override public boolean toggle(@NotNull Player player) { return svc.toggle(player); }
    @Override public boolean setVanished(@NotNull Player player, boolean vanish) { return svc.setVanished(player, vanish); }
}
