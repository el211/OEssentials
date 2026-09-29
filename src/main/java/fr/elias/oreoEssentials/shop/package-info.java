/**
 * shop feature: lifecycle wiring and the implementation it owns.
 *
 * <p>{@code lifecycle} exposes the MinecraftModulith service contract and startup
 * entry point. {@code internal} contains gameplay, commands, listeners and persistence
 * belonging to this feature. Existing cross-feature implementation references are
 * tracked separately from lifecycle dependencies; these packages are not hot-unloadable.
 *
 * @see fr.elias.oreoEssentials.shop.lifecycle.ShopModule
 */
@fr.elias.oreoEssentials.platform.modularity.FeaturePackage(
        id = "shop", lifecycle = fr.elias.oreoEssentials.shop.lifecycle.ShopModule.class)
package fr.elias.oreoEssentials.shop;
