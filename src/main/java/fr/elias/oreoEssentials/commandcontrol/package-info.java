/**
 * command-control feature: lifecycle wiring and the implementation it owns.
 *
 * <p>{@code lifecycle} exposes the MinecraftModulith service contract and startup
 * entry point. {@code internal} contains gameplay, commands, listeners and persistence
 * belonging to this feature. Existing cross-feature implementation references are
 * tracked separately from lifecycle dependencies; these packages are not hot-unloadable.
 *
 * @see fr.elias.oreoEssentials.commandcontrol.lifecycle.CommandControlModule
 */
@fr.elias.oreoEssentials.platform.modularity.FeaturePackage(
        id = "command-control", lifecycle = fr.elias.oreoEssentials.commandcontrol.lifecycle.CommandControlModule.class)
package fr.elias.oreoEssentials.commandcontrol;
