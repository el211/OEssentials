package fr.elias.oreoEssentials.punishment.internal.api;

import fr.elias.oreoEssentials.api.IPunishmentAPI;
import fr.elias.oreoEssentials.punishment.internal.PunishmentLogger;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class PunishmentAPIImpl implements IPunishmentAPI {
    private final PunishmentLogger logger;
    public PunishmentAPIImpl(PunishmentLogger logger) { this.logger = logger; }

    @Override public @NotNull List<PunishmentLogger.PunishEntry> getHistory(@NotNull UUID id) { return logger.getHistory(id); }
    @Override public void clearHistory(@NotNull UUID id) { logger.clearHistory(id); }
}
