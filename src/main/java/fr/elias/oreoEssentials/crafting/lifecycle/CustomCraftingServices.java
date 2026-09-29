package fr.elias.oreoEssentials.crafting.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.crafting.internal.customcraft.CustomCraftingService;

@ModuleApi("services")
public interface CustomCraftingServices {
    CustomCraftingService getCustomCraftingService();
}
