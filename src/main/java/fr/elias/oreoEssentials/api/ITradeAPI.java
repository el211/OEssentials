package fr.elias.oreoEssentials.api;

import fr.elias.oreoEssentials.trade.internal.TradeSession;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * API for the Trade module.
 *
 * <p>Obtain via {@link OreoEssentialsAPI#trade()}. Returns {@code null} if the
 * trade module is disabled.
 *
 * <h2>Example — check if a player is in a trade before teleporting them</h2>
 * <pre>{@code
 * ITradeAPI trade = OreoEssentialsAPI.get().trade();
 * if (trade != null && trade.isInTrade(player.getUniqueId())) {
 *     player.sendMessage("You cannot teleport while trading!");
 *     return;
 * }
 * }</pre>
 */
public interface ITradeAPI {

    /**
     * Sends a trade invite from {@code requester} to {@code target}.
     *
     * @param requester the player sending the invite
     * @param target    the player receiving the invite
     */
    void sendInvite(@NotNull Player requester, @NotNull Player target);

    /**
     * Returns {@code true} if the player is currently in an active trade session.
     *
     * @param playerId the player's UUID
     */
    boolean isInTrade(@NotNull UUID playerId);

    /**
     * Returns the ID of the trade session the player is in, or {@code null} if
     * they are not currently trading.
     *
     * @param playerId the player's UUID
     */
    @Nullable
    UUID getTradeIdByPlayer(@NotNull UUID playerId);

    /**
     * Returns the {@link TradeSession} for the given session ID, or {@code null}
     * if it no longer exists.
     *
     * @param tradeId the session ID (as returned by {@link #getTradeIdByPlayer})
     */
    @Nullable
    TradeSession getSession(@NotNull UUID tradeId);

    /**
     * Forcefully cancels the trade session of a player and returns their items.
     *
     * @param playerId the player's UUID
     * @param reason   a human-readable reason (logged / shown to both parties)
     */
    void cancelTradeFor(@NotNull UUID playerId, @NotNull String reason);
}
