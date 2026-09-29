package fr.elias.oreoEssentials.punishment.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;

@PluginModule(value = "punishment-logger", dependencies = {"warnings"})
public final class PunishmentLoggerModule extends ManagedModule implements PunishmentLoggerServices {
    private fr.elias.oreoEssentials.punishment.internal.PunishmentLogger punishmentLogger;

    @Override
    protected void start() {
        initPunishmentLogger();
    }

    @Override public fr.elias.oreoEssentials.punishment.internal.PunishmentLogger getPunishmentLogger() { return punishmentLogger; }

    private void initPunishmentLogger() {
        try {
            this.punishmentLogger = new fr.elias.oreoEssentials.punishment.internal.PunishmentLogger(plugin);
            plugin.getLogger().info("[PunishmentLogger] Initialized.");
        } catch (Throwable t) {
            plugin.getLogger().severe("[PunishmentLogger] Failed to initialize: " + t.getMessage());
        }
    }
}
