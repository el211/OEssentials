package fr.elias.oreoEssentials.modgui.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface ModGuiServices {
    fr.elias.oreoEssentials.modgui.internal.ModGuiService getModGuiService();
}
