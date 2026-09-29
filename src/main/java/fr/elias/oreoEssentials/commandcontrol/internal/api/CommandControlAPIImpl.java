package fr.elias.oreoEssentials.commandcontrol.internal.api;

import fr.elias.oreoEssentials.api.ICommandControlAPI;
import fr.elias.oreoEssentials.commandcontrol.internal.CommandControlService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandControlAPIImpl implements ICommandControlAPI {
    private final CommandControlService svc;
    public CommandControlAPIImpl(CommandControlService svc) { this.svc = svc; }

    @Override public boolean isEnabled() { return svc.isEnabled(); }
    @Override public boolean canBypass(@NotNull Player p) { return svc.canBypass(p); }
    @Override public boolean isBlocked(@NotNull Player p, @NotNull String root, @NotNull String sub, @NotNull String raw) { return svc.isBlocked(p, root, sub, raw); }
    @Override public boolean canUseSub(@NotNull Player p, @NotNull String root, @NotNull String sub) { return svc.canUseSub(p, root, sub); }
}
