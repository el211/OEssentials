package fr.elias.oessentials.tab.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface TabServices {
    fr.elias.oessentials.tab.internal.TabListManager getTabListManager();
}
