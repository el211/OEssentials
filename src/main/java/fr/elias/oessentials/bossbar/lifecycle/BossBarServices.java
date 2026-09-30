package fr.elias.oessentials.bossbar.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface BossBarServices {
    fr.elias.oessentials.bossbar.internal.BossBarService getBossBarService();
}
