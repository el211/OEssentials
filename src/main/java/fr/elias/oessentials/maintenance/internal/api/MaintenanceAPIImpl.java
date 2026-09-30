package fr.elias.oessentials.maintenance.internal.api;

import fr.elias.oessentials.api.IMaintenanceAPI;
import fr.elias.oessentials.maintenance.internal.MaintenanceService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MaintenanceAPIImpl implements IMaintenanceAPI {
    private final MaintenanceService svc;
    public MaintenanceAPIImpl(MaintenanceService svc) { this.svc = svc; }

    @Override public boolean isEnabled() { return svc.isEnabled(); }
    @Override public void enable() { svc.enable(); }
    @Override public void disable() { svc.disable(); }
    @Override public boolean toggle() { return svc.toggle(); }
    @Override public boolean canJoin(@NotNull Player p) { return svc.canJoin(p); }
    @Override public void addToWhitelist(@NotNull UUID id) { svc.addToWhitelist(id); }
    @Override public void removeFromWhitelist(@NotNull UUID id) { svc.removeFromWhitelist(id); }
    @Override public void clearWhitelist() { svc.clearWhitelist(); }
    @Override public void kickNonWhitelistedPlayers() { svc.kickNonWhitelistedPlayers(); }
    @Override public void setTimer(long ms) { svc.setTimer(ms); }
    @Override public void removeTimer() { svc.removeTimer(); }
    @Override public @Nullable String getFormattedTimeRemaining() { return svc.getFormattedTimeRemaining(); }
}
