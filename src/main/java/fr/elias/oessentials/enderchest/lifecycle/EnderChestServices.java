package fr.elias.oessentials.enderchest.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface EnderChestServices {
    fr.elias.oessentials.enderchest.internal.EnderChestConfig getEcConfig();
    fr.elias.oessentials.enderchest.internal.EnderChestService getEcService();
}
