package fr.elias.oessentials.playervaults.internal.api;

import fr.elias.oessentials.api.IPlayerVaultsAPI;
import fr.elias.oessentials.playervaults.internal.PlayerVaultsService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerVaultsAPIImpl implements IPlayerVaultsAPI {
    private final PlayerVaultsService svc;
    public PlayerVaultsAPIImpl(PlayerVaultsService svc) { this.svc = svc; }

    @Override public boolean isEnabled() { return svc.enabled(); }
    @Override public void openMenu(@NotNull Player p) { svc.openMenu(p); }
    @Override public void openVault(@NotNull Player p, int id) { svc.openVault(p, id); }
    @Override public boolean canAccess(@NotNull Player p, int id) { return svc.canAccess(p, id); }
    @Override public int resolveSlots(@NotNull Player p, int id) { return svc.resolveSlots(p, id); }
}
