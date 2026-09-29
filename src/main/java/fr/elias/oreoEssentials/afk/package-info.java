/**
 * afk feature: lifecycle wiring and the implementation it owns.
 *
 * <p>{@code lifecycle} exposes the MinecraftModulith service contract and startup
 * entry point. {@code internal} contains gameplay, commands, listeners and persistence
 * belonging to this feature. Existing cross-feature implementation references are
 * tracked separately from lifecycle dependencies; these packages are not hot-unloadable.
 *
 * @see fr.elias.oreoEssentials.afk.lifecycle.AfkModule
 */
@fr.elias.oreoEssentials.platform.modularity.FeaturePackage(
        id = "afk", lifecycle = fr.elias.oreoEssentials.afk.lifecycle.AfkModule.class)
package fr.elias.oreoEssentials.afk;
