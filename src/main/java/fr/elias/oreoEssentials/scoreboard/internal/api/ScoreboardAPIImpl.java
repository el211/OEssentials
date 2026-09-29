package fr.elias.oreoEssentials.scoreboard.internal.api;

import fr.elias.oreoEssentials.api.IScoreboardAPI;
import fr.elias.oreoEssentials.scoreboard.internal.ScoreboardService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ScoreboardAPIImpl implements IScoreboardAPI {
    private final ScoreboardService svc;
    public ScoreboardAPIImpl(ScoreboardService svc) { this.svc = svc; }

    @Override public boolean isShown(@NotNull Player player) { return svc.isShown(player); }
    @Override public void toggle(@NotNull Player player) { svc.toggle(player); }
    @Override public void show(@NotNull Player player) { svc.show(player); }
    @Override public void hide(@NotNull Player player) { svc.hide(player); }
    @Override public void reload() { svc.reload(); }
}
