package fr.elias.oreoEssentials.api;

import fr.elias.oreoEssentials.crafting.internal.customcraft.CustomRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

/**
 * API for the CustomCraft module (custom shaped/shapeless recipes).
 * Obtain via {@link OreoEssentialsAPI#customCraft()}. Returns {@code null} if disabled.
 */
public interface ICustomCraftAPI {

    /** Returns the number of registered custom recipes. */
    int getRecipeCount();

    /** Returns the names of all registered custom recipes. */
    @NotNull Set<String> allNames();

    /**
     * Returns the custom recipe with the given name, or empty if not found.
     *
     * @param name the recipe name (case-sensitive)
     */
    @NotNull Optional<CustomRecipe> get(@NotNull String name);

    /**
     * Saves and registers a new custom recipe.
     *
     * @param recipe the recipe to register
     * @return {@code true} if saved successfully
     */
    boolean saveAndRegister(@NotNull CustomRecipe recipe);

    /**
     * Deletes the custom recipe with the given name and unregisters it.
     *
     * @param name the recipe name
     * @return {@code true} if it existed and was removed
     */
    boolean delete(@NotNull String name);

    /**
     * Returns the permission required to use a recipe, or {@code null} if none.
     *
     * @param name the recipe name
     */
    @NotNull Optional<String> getPermissionFor(@NotNull String name);
}
