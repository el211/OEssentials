package fr.elias.oreoEssentials.jumppads.internal.api;

import fr.elias.oreoEssentials.api.IJumpPadsAPI;
import fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsManager;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class JumpPadsAPIImpl implements IJumpPadsAPI {
    private final JumpPadsManager mgr;
    public JumpPadsAPIImpl(JumpPadsManager mgr) { this.mgr = mgr; }

    @Override public @NotNull Set<String> listNames() { return mgr.listNames(); }
    @Override public boolean create(@NotNull String name, @NotNull Location loc, double power, double upward, boolean useLookDir) { return mgr.create(name, loc, power, upward, useLookDir); }
    @Override public boolean remove(@NotNull String name) { return mgr.remove(name); }
}
