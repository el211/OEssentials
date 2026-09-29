package fr.elias.oessentials.playtime.internal.api;

import fr.elias.oessentials.api.IPlaytimeAPI;
import fr.elias.oessentials.playtime.internal.PlaytimeRewardsService;
import fr.elias.oessentials.playtime.internal.PlaytimeTracker;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class PlaytimeAPIImpl implements IPlaytimeAPI {
    private final PlaytimeTracker tracker;
    private final PlaytimeRewardsService rewards;

    public PlaytimeAPIImpl(PlaytimeTracker tracker, PlaytimeRewardsService rewards) {
        this.tracker = tracker;
        this.rewards = rewards;
    }

    @Override public long getSeconds(@NotNull UUID playerId) { return tracker.getSeconds(playerId); }
    @Override public boolean isRewardsEnabled() { return rewards.isEnabled(); }
    @Override public @NotNull List<String> rewardsReady(@NotNull Player player) { return rewards.rewardsReady(player); }
    @Override public boolean claim(@NotNull Player player, @NotNull String rewardId) { return rewards.claim(player, rewardId, true); }
}
