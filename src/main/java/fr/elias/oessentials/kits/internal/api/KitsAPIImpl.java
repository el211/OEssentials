package fr.elias.oessentials.kits.internal.api;

import fr.elias.oessentials.api.IKitsAPI;
import fr.elias.oessentials.kits.internal.Kit;
import fr.elias.oessentials.kits.internal.KitsManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class KitsAPIImpl implements IKitsAPI {
    private final KitsManager mgr;
    public KitsAPIImpl(KitsManager mgr) { this.mgr = mgr; }

    @Override public boolean isEnabled() { return mgr.isEnabled(); }
    @Override public @NotNull Map<String, Kit> getKits() { return mgr.getKits(); }
    @Override public @Nullable Kit getKit(@NotNull String id) { return mgr.getKits().get(id.toLowerCase()); }
    @Override public long getSecondsLeft(@NotNull Player p, @NotNull Kit kit) { return mgr.getSecondsLeft(p, kit); }
    @Override public boolean claim(@NotNull Player p, @NotNull String kitId) { return mgr.claim(p, kitId); }
}
