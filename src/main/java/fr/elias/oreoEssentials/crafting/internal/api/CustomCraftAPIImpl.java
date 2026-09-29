package fr.elias.oreoEssentials.crafting.internal.api;

import fr.elias.oreoEssentials.api.ICustomCraftAPI;
import fr.elias.oreoEssentials.crafting.internal.customcraft.CustomCraftingService;
import fr.elias.oreoEssentials.crafting.internal.customcraft.CustomRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public class CustomCraftAPIImpl implements ICustomCraftAPI {
    private final CustomCraftingService svc;
    public CustomCraftAPIImpl(CustomCraftingService svc) { this.svc = svc; }

    @Override public int getRecipeCount() { return svc.getRecipeCount(); }
    @Override public @NotNull Set<String> allNames() { return svc.allNames(); }
    @Override public @NotNull Optional<CustomRecipe> get(@NotNull String name) { return svc.get(name); }
    @Override public boolean saveAndRegister(@NotNull CustomRecipe r) { return svc.saveAndRegister(r); }
    @Override public boolean delete(@NotNull String name) { return svc.delete(name); }
    @Override public @NotNull Optional<String> getPermissionFor(@NotNull String name) { return svc.getPermissionFor(name); }
}
