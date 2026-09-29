package fr.elias.oessentials.warnings.internal.api;

import fr.elias.oessentials.api.IWarningsAPI;
import fr.elias.oessentials.warnings.internal.WarnService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class WarningsAPIImpl implements IWarningsAPI {
    private final WarnService svc;
    public WarningsAPIImpl(WarnService svc) { this.svc = svc; }

    @Override public int warn(@NotNull UUID target, @NotNull UUID issuer, @NotNull String issuerName, @NotNull String reason) { return svc.warn(target, issuer, issuerName, reason); }
    @Override public boolean removeById(@NotNull UUID target, @NotNull String warnId) { return svc.removeById(target, warnId); }
    @Override public void clearAll(@NotNull UUID target) { svc.clearAll(target); }
    @Override public @NotNull List<WarnService.WarnEntry> getActive(@NotNull UUID target) { return svc.getActive(target); }
    @Override public int getActiveCount(@NotNull UUID target) { return svc.getActiveCount(target); }
    @Override public int getMaxWarnings() { return svc.getMaxWarnings(); }
}
