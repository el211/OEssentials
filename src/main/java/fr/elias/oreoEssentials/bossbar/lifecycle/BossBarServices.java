package fr.elias.oreoEssentials.bossbar.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface BossBarServices {
    fr.elias.oreoEssentials.bossbar.internal.BossBarService getBossBarService();
}
