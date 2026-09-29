package fr.elias.oessentials.jails.internal.api;

import fr.elias.oessentials.api.IJailAPI;
import fr.elias.oessentials.jails.internal.jail.JailModels;
import fr.elias.oessentials.jails.internal.jail.JailService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class JailAPIImpl implements IJailAPI {
    private final JailService svc;
    public JailAPIImpl(JailService svc) { this.svc = svc; }

    @Override public boolean isJailed(@NotNull UUID id) { return svc.isJailed(id); }
    @Override public @Nullable JailModels.Sentence getSentence(@NotNull UUID id) { return svc.sentence(id); }
    @Override public @NotNull Map<String, JailModels.Jail> allJails() { return svc.allJails(); }
    @Override public @Nullable JailModels.Jail getJail(@NotNull String name) { return svc.getJail(name); }
    @Override public boolean release(@NotNull UUID id) { return svc.release(id); }
    @Override public boolean extendSentence(@NotNull UUID id, long ms, @NotNull String by) { return svc.extendSentence(id, ms, by); }
    @Override public boolean isCommandBlockedFor(@NotNull Player p, @NotNull String cmd) { return svc.isCommandBlockedFor(p, cmd); }
}
