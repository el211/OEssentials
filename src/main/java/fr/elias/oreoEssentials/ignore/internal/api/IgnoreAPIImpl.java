package fr.elias.oreoEssentials.ignore.internal.api;

import fr.elias.oreoEssentials.api.IIgnoreAPI;
import fr.elias.oreoEssentials.ignore.internal.IgnoreService;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class IgnoreAPIImpl implements IIgnoreAPI {
    private final IgnoreService svc;
    public IgnoreAPIImpl(IgnoreService svc) { this.svc = svc; }

    @Override public boolean isIgnoring(@NotNull UUID player, @NotNull UUID target) { return svc.isIgnoring(player, target); }
    @Override public boolean ignore(@NotNull UUID player, @NotNull UUID target) { return svc.ignore(player, target); }
    @Override public boolean unignore(@NotNull UUID player, @NotNull UUID target) { return svc.unignore(player, target); }
    @Override public @NotNull Set<UUID> getIgnoredBy(@NotNull UUID player) { return svc.getIgnoredBy(player); }
    @Override public void clearIgnoreList(@NotNull UUID player) { svc.clearIgnoreList(player); }
}
