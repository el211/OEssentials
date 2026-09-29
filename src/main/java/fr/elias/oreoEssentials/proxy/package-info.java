/**
 * proxy-messaging feature: lifecycle wiring and the implementation it owns.
 *
 * <p>{@code lifecycle} exposes the MinecraftModulith service contract and startup
 * entry point. {@code internal} contains gameplay, commands, listeners and persistence
 * belonging to this feature. Existing cross-feature implementation references are
 * tracked separately from lifecycle dependencies; these packages are not hot-unloadable.
 *
 * @see fr.elias.oreoEssentials.proxy.lifecycle.ProxyMessagingModule
 */
@fr.elias.oreoEssentials.platform.modularity.FeaturePackage(
        id = "proxy-messaging", lifecycle = fr.elias.oreoEssentials.proxy.lifecycle.ProxyMessagingModule.class)
package fr.elias.oreoEssentials.proxy;
