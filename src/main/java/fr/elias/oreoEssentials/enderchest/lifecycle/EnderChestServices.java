package fr.elias.oreoEssentials.enderchest.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface EnderChestServices {
    fr.elias.oreoEssentials.enderchest.internal.EnderChestConfig getEcConfig();
    fr.elias.oreoEssentials.enderchest.internal.EnderChestService getEcService();
}
