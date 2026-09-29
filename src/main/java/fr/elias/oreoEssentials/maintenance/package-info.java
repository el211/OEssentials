/**
 * maintenance feature: lifecycle wiring and the implementation it owns.
 *
 * <p>{@code lifecycle} exposes the MinecraftModulith service contract and startup
 * entry point. {@code internal} contains gameplay, commands, listeners and persistence
 * belonging to this feature. Existing cross-feature implementation references are
 * tracked separately from lifecycle dependencies; these packages are not hot-unloadable.
 *
 * @see fr.elias.oreoEssentials.maintenance.lifecycle.MaintenanceModule
 */
@fr.elias.oreoEssentials.platform.modularity.FeaturePackage(
        id = "maintenance", lifecycle = fr.elias.oreoEssentials.maintenance.lifecycle.MaintenanceModule.class)
package fr.elias.oreoEssentials.maintenance;
