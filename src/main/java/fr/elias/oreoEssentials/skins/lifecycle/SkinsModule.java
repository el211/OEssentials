package fr.elias.oreoEssentials.skins.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.skins.internal.skin.SkinDebug;
import fr.elias.oreoEssentials.skins.internal.skin.SkinRefresherBootstrap;

@PluginModule(value = "skins", dependencies = {"command-control"})
public final class SkinsModule extends ManagedModule implements SkinsServices {

    @Override
    protected void start() {
        initSkins();
    }

    private void initSkins() {
        SkinRefresherBootstrap.init(plugin);
        SkinDebug.init(plugin);
    }
}
