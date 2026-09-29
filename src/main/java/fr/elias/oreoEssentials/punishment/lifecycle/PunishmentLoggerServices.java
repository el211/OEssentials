package fr.elias.oreoEssentials.punishment.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PunishmentLoggerServices {
    fr.elias.oreoEssentials.punishment.internal.PunishmentLogger getPunishmentLogger();
}
