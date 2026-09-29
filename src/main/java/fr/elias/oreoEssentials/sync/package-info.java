/**
 * inventory-sync feature: lifecycle wiring and the implementation it owns.
 *
 * <p>{@code lifecycle} exposes the MinecraftModulith service contract and startup
 * entry point. {@code internal} contains gameplay, commands, listeners and persistence
 * belonging to this feature. Existing cross-feature implementation references are
 * tracked separately from lifecycle dependencies; these packages are not hot-unloadable.
 *
 * @see fr.elias.oreoEssentials.sync.lifecycle.InventorySyncModule
 */
@fr.elias.oreoEssentials.platform.modularity.FeaturePackage(
        id = "inventory-sync", lifecycle = fr.elias.oreoEssentials.sync.lifecycle.InventorySyncModule.class)
package fr.elias.oreoEssentials.sync;
