package fr.elias.oessentials.mobs.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface MobsServices {
    fr.elias.oessentials.mobs.internal.HealthBarListener getHealthBarListener();
}
