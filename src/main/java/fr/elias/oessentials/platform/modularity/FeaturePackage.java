package fr.elias.oessentials.platform.modularity;

import dev.oreo.modulith.core.MinecraftModule;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Connects a feature's package-info.java to its MinecraftModulith lifecycle. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface FeaturePackage {
    String id();
    Class<? extends MinecraftModule> lifecycle();
}
