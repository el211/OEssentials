package fr.elias.oreoEssentials.chat.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.chat.internal.ChatSyncManager;
import fr.elias.oreoEssentials.chat.internal.CustomConfig;
import fr.elias.oreoEssentials.chat.internal.FormatManager;

@ModuleApi("services")
public interface ChatServices {
    fr.elias.oreoEssentials.chat.internal.channels.ChatChannelManager getChannelManager();
    CustomConfig getChatConfig();
    FormatManager getChatFormatManager();
    ChatSyncManager getChatSyncManager();
    org.bukkit.event.Listener getActiveChatListener();
    org.bukkit.event.Listener getActiveJoinMessagesListener();
    org.bukkit.event.Listener getActiveQuitMessagesListener();
    org.bukkit.configuration.file.FileConfiguration getJoinQuitCfg();
    fr.elias.oreoEssentials.messaging.internal.oreobotfeatures.listeners.ConversationListener getActiveConversationListener();
    void reloadChat();
}
