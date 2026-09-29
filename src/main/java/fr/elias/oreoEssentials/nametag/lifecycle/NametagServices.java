package fr.elias.oreoEssentials.nametag.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.nametag.internal.PlayerNametagManager;

@ModuleApi("services")
public interface NametagServices {
    PlayerNametagManager getNametagManager();
    fr.elias.oreoEssentials.nametag.internal.ChatBubbleService getChatBubbleService();
    fr.elias.oreoEssentials.nametag.internal.ActionBarService getActionBarService();
    fr.elias.oreoEssentials.nametag.internal.MultiBossBarService getMultiBossBarService();
    fr.elias.oreoEssentials.nametag.internal.CustomNameplatesConfig getCustomNameplatesConfig();
    fr.elias.oreoEssentials.nametag.internal.NametageToggleStore getNametagToggleStore();
    void reloadCustomNameplates();
}
