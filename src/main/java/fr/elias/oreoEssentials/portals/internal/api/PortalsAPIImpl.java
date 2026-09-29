package fr.elias.oreoEssentials.portals.internal.api;

import fr.elias.oreoEssentials.api.IPortalsAPI;
import fr.elias.oreoEssentials.portals.internal.PortalsManager;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class PortalsAPIImpl implements IPortalsAPI {
    private final PortalsManager mgr;
    public PortalsAPIImpl(PortalsManager mgr) { this.mgr = mgr; }

    @Override public boolean isEnabled() { return mgr.isEnabled(); }
    @Override public @NotNull Set<String> listNames() { return mgr.listNames(); }
    @Override public @Nullable PortalsManager.Portal get(@NotNull String name) { return mgr.get(name); }
    @Override public boolean remove(@NotNull String name) { return mgr.remove(name); }
    @Override public void updateDestination(@NotNull String name, @NotNull Location loc) { mgr.updateDestination(name, loc); }
    @Override public void updateDestServer(@NotNull String name, @NotNull String server) { mgr.updateDestServer(name, server); }
    @Override public void updatePermission(@NotNull String name, @NotNull String perm) { mgr.updatePermission(name, perm); }
}
