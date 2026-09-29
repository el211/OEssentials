package fr.elias.oreoEssentials.worlds.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;

@PluginModule(value = "custom-worlds", dependencies = {"currency-system"})
public final class CustomWorldsModule extends ManagedModule implements CustomWorldsServices {

    @Override
    protected void start() {
        initCustomWorlds();
    }

    private void initCustomWorlds() { fr.elias.oreoEssentials.worlds.internal.commands.core.admins.OeWorldCommand.loadCustomWorlds(plugin); }
}
