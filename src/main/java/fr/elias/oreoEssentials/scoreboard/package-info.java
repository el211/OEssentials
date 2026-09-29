/**
 * scoreboard feature: lifecycle wiring and the implementation it owns.
 *
 * <p>{@code lifecycle} exposes the MinecraftModulith service contract and startup
 * entry point. {@code internal} contains gameplay, commands, listeners and persistence
 * belonging to this feature. Existing cross-feature implementation references are
 * tracked separately from lifecycle dependencies; these packages are not hot-unloadable.
 *
 * @see fr.elias.oreoEssentials.scoreboard.lifecycle.ScoreboardModule
 */
@fr.elias.oreoEssentials.platform.modularity.FeaturePackage(
        id = "scoreboard", lifecycle = fr.elias.oreoEssentials.scoreboard.lifecycle.ScoreboardModule.class)
package fr.elias.oreoEssentials.scoreboard;
