package fr.elias.oessentials.afk.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.afk.internal.AfkPoolService;
import fr.elias.oessentials.afk.internal.AfkService;

@ModuleApi("services")
public interface AfkServices {
    AfkService getAfkService();
    AfkPoolService getAfkPoolService();
}
