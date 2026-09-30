package fr.elias.oessentials.webpanel.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface WebPanelServices {
    fr.elias.oessentials.webpanel.internal.WebPanelSyncService getWebPanelSyncService();
}
