package fr.elias.oessentials.punishment.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface PunishmentLoggerServices {
    fr.elias.oessentials.punishment.internal.PunishmentLogger getPunishmentLogger();
}
