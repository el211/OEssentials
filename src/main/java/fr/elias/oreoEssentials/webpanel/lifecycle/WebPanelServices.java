package fr.elias.oreoEssentials.webpanel.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface WebPanelServices {
    fr.elias.oreoEssentials.webpanel.internal.WebPanelSyncService getWebPanelSyncService();
}
