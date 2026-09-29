package fr.elias.oreoEssentials.events.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "events", dependencies = {"configuration::services", "web-panel"})
public final class EventsModule extends ManagedModule implements EventsServices {
    private fr.elias.oreoEssentials.events.internal.EventConfig eventConfig;
    private fr.elias.oreoEssentials.events.internal.DeathMessageService deathMessages;

    @Override
    protected void start() {
        initEvents();
    }

    @Override public fr.elias.oreoEssentials.events.internal.EventConfig getEventConfig() { return eventConfig; }
    @Override public fr.elias.oreoEssentials.events.internal.DeathMessageService getDeathMessages() { return deathMessages; }

    private void initEvents() {
        this.eventConfig   = new fr.elias.oreoEssentials.events.internal.EventConfig(plugin.getDataFolder());
        this.deathMessages = new fr.elias.oreoEssentials.events.internal.DeathMessageService(plugin.getDataFolder());

        var eventEngine = new fr.elias.oreoEssentials.events.internal.EventEngine(eventConfig, deathMessages);
        plugin.getServer().getPluginManager().registerEvents(eventEngine, plugin);

        var eventCmd = new fr.elias.oreoEssentials.events.internal.EventCommands(eventConfig, deathMessages);
        services(ConfigurationServices.class).getCommands().registerLegacy("oevents", eventCmd);
    }
}
