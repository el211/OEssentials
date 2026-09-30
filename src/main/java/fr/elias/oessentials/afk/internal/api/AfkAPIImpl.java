package fr.elias.oessentials.afk.internal.api;

import fr.elias.oessentials.api.IAfkAPI;
import fr.elias.oessentials.afk.internal.AfkService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AfkAPIImpl implements IAfkAPI {
    private final AfkService svc;
    public AfkAPIImpl(AfkService svc) { this.svc = svc; }

    @Override public boolean isAfk(@NotNull Player p) { return svc.isAfk(p); }
    @Override public long getAfkForSeconds(@NotNull Player p) { return svc.getAfkForSeconds(p); }
    @Override public long getInactiveForSeconds(@NotNull Player p) { return svc.getInactiveForSeconds(p); }
    @Override public boolean toggleAfk(@NotNull Player p) { return svc.toggleAfk(p); }
    @Override public void setAfk(@NotNull Player p, boolean afk) { svc.setAfk(p, afk); }
    @Override public void clearAfk(@NotNull Player p) { svc.clearAfk(p); }
}
