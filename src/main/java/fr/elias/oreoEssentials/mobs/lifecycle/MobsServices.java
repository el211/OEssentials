package fr.elias.oreoEssentials.mobs.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface MobsServices {
    fr.elias.oreoEssentials.mobs.internal.HealthBarListener getHealthBarListener();
}
