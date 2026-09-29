package fr.elias.oreoEssentials.chat.internal.api;

import fr.elias.oreoEssentials.api.IChatAPI;
import fr.elias.oreoEssentials.chat.internal.ChatSyncManager;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ChatAPIImpl implements IChatAPI {

    private final ChatSyncManager manager;

    public ChatAPIImpl(ChatSyncManager manager) {
        this.manager = manager;
    }

    @Override
    public void broadcastSystemMessage(@NotNull String message) {
        manager.broadcastSystemMessage(message);
    }

    @Override
    public void broadcastMute(@NotNull UUID playerId, long untilEpochMillis,
                              @NotNull String reason, @NotNull String by) {
        manager.broadcastMute(playerId, untilEpochMillis, reason, by);
    }

    @Override
    public void broadcastUnmute(@NotNull UUID playerId) {
        manager.broadcastUnmute(playerId);
    }

    @Override
    public void publishMessage(@NotNull UUID playerId, @NotNull String serverName,
                               @NotNull String playerName, @NotNull String message) {
        manager.publishMessage(playerId, serverName, playerName, message);
    }
}
