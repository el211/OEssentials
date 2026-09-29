package fr.elias.oessentials.commandcontrol.internal.api;

import fr.elias.oessentials.api.IAliasesAPI;
import fr.elias.oessentials.commandcontrol.internal.aliases.AliasService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AliasesAPIImpl implements IAliasesAPI {
    private final AliasService svc;
    public AliasesAPIImpl(AliasService svc) { this.svc = svc; }

    @Override public @NotNull Map<String, AliasService.AliasDef> all() { return svc.all(); }
    @Override public boolean exists(@NotNull String name) { return svc.exists(name); }
    @Override public @Nullable AliasService.AliasDef get(@NotNull String name) { return svc.get(name); }
    @Override public void put(@NotNull AliasService.AliasDef def) { svc.put(def); }
    @Override public void remove(@NotNull String name) { svc.remove(name); }
    @Override public void load() { svc.load(); }
    @Override public void save() { svc.save(); svc.applyRuntimeRegistration(); }
}
