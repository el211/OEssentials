package fr.elias.oessentials.economy.internal.api;

import fr.elias.oessentials.api.IEconomyAPI;
import fr.elias.oessentials.economy.internal.EconomyService;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EconomyAPIImpl implements IEconomyAPI {
    private final EconomyService svc;
    public EconomyAPIImpl(EconomyService svc) { this.svc = svc; }

    @Override public double getBalance(@NotNull UUID id) { return svc.getBalance(id); }
    @Override public boolean deposit(@NotNull UUID id, double amount) { return svc.deposit(id, amount); }
    @Override public boolean withdraw(@NotNull UUID id, double amount) { return svc.withdraw(id, amount); }
    @Override public boolean transfer(@NotNull UUID from, @NotNull UUID to, double amount) { return svc.transfer(from, to, amount); }
}
