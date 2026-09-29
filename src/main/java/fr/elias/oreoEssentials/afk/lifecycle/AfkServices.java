package fr.elias.oreoEssentials.afk.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.afk.internal.AfkPoolService;
import fr.elias.oreoEssentials.afk.internal.AfkService;

@ModuleApi("services")
public interface AfkServices {
    AfkService getAfkService();
    AfkPoolService getAfkPoolService();
}
