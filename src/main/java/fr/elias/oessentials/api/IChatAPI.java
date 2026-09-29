package fr.elias.oessentials.api;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for the Chat / Moderation module.
 *
 * <p>Obtain via {@link OEssentialsAPI#chat()}. Returns {@code null} if the
 * chat sync module is disabled.
 *
 * <h2>Example — broadcast a system message across all servers</h2>
 * <pre>{@code
 * IChatAPI chat = OEssentialsAPI.get().chat();
 * if (chat != null) {
 *     chat.broadcastSystemMessage("<red>Server restart in 5 minutes!");
 * }
 * }</pre>
 */
public interface IChatAPI {

    /**
     * Broadcasts a plain-text (or MiniMessage) system message to all servers.
     *
     * @param message the message to broadcast
     */
    void broadcastSystemMessage(@NotNull String message);

    /**
     * Broadcasts a mute for the given player to all servers.
     *
     * @param playerId        the muted player's UUID
     * @param untilEpochMillis expiry timestamp in epoch milliseconds ({@code 0} = permanent)
     * @param reason          reason shown to the player
     * @param by              name of the moderator or system that issued the mute
     */
    void broadcastMute(@NotNull UUID playerId,
                       long untilEpochMillis,
                       @NotNull String reason,
                       @NotNull String by);

    /**
     * Broadcasts an unmute for the given player to all servers.
     *
     * @param playerId the player's UUID
     */
    void broadcastUnmute(@NotNull UUID playerId);

    /**
     * Publishes a chat message originating from a player on this server to all
     * other servers via RabbitMQ.
     *
     * @param playerId   the sender's UUID
     * @param serverName the name of the originating server
     * @param playerName the player's display/real name
     * @param message    the raw chat message
     */
    void publishMessage(@NotNull UUID playerId,
                        @NotNull String serverName,
                        @NotNull String playerName,
                        @NotNull String message);
}
