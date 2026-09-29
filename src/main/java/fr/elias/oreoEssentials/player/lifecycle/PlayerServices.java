package fr.elias.oreoEssentials.player.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.player.internal.back.service.BackService;
import fr.elias.oreoEssentials.player.internal.deathback.DeathBackService;
import fr.elias.oreoEssentials.storage.internal.homes.TeleportBroker;
import fr.elias.oreoEssentials.player.internal.tp.service.TeleportService;
import fr.elias.oreoEssentials.player.internal.services.GodService;
import fr.elias.oreoEssentials.player.internal.services.MessageService;
import fr.elias.oreoEssentials.player.internal.services.VanishService;

@ModuleApi("services")
public interface PlayerServices {
    TeleportService getTeleportService();
    BackService getBackService();
    MessageService getMessageService();
    DeathBackService getDeathBackService();
    GodService getGodService();
    VanishService getVanishService();
    TeleportBroker getTeleportBroker();
}
