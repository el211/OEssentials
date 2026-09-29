package fr.elias.oreoEssentials.tab.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface TabServices {
    fr.elias.oreoEssentials.tab.internal.TabListManager getTabListManager();
}
