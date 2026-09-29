package fr.elias.oreoEssentials.commands.internal.api;

import fr.elias.oreoEssentials.api.IInteractiveCommandsAPI;
import fr.elias.oreoEssentials.commands.internal.ic.ICEntry;
import fr.elias.oreoEssentials.commands.internal.ic.ICManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class InteractiveCommandsAPIImpl implements IInteractiveCommandsAPI {
    private final ICManager mgr;
    public InteractiveCommandsAPIImpl(ICManager mgr) { this.mgr = mgr; }

    @Override public @Nullable ICEntry get(@NotNull String name) { return mgr.get(name); }
    @Override public @NotNull ICEntry create(@NotNull String name) { return mgr.create(name); }
    @Override public @NotNull Collection<ICEntry> all() { return mgr.all(); }
    @Override public void reload() { mgr.reload(); }
    @Override public void save() { mgr.save(); }
}
