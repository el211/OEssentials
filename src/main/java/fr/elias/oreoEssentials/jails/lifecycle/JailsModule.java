package fr.elias.oreoEssentials.jails.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;
import fr.elias.oreoEssentials.storage.lifecycle.StorageServices;
import fr.elias.oreoEssentials.storage.internal.playerwarp.PlayerWarpWhitelistCommand;
import fr.elias.oreoEssentials.storage.internal.playerwarp.command.PlayerWarpCommand;

@PluginModule(value = "jails", dependencies = {"commands", "configuration::services", "storage::services"})
public final class JailsModule extends ManagedModule implements JailsServices {
    private fr.elias.oreoEssentials.jails.internal.jail.JailService jailService;

    @Override
    protected void start() {
        cleanup("jailService", () -> { if (jailService != null) jailService.disable(); });
        initJails();
    }

    @Override public fr.elias.oreoEssentials.jails.internal.jail.JailService getJailService() { return jailService; }

    private void initJails() {
        final String essentialsStorage = plugin.getConfig().getString("essentials.storage", "yaml").toLowerCase();
        fr.elias.oreoEssentials.jails.internal.jail.JailStorage jailStorage;

        if (plugin.getConfig().getBoolean("Jail.Storage.Mongo.Enabled", false)
                && "mongodb".equalsIgnoreCase(essentialsStorage)
                && services(StorageServices.class).getHomesMongoClient() != null) {
            String mongoDb = plugin.getConfig().getString("storage.mongo.database", "oreo");
            jailStorage = new fr.elias.oreoEssentials.jails.internal.jail.MongoJailStorage(plugin.getConfig().getString("storage.mongo.uri", "mongodb://localhost:27017"), mongoDb);
            plugin.getLogger().info("[Jails] Using MongoDB storage.");
        } else {
            jailStorage = new fr.elias.oreoEssentials.jails.internal.jail.YamlJailStorage(plugin);
            plugin.getLogger().info("[Jails] Using YAML storage.");
        }

        this.jailService = new fr.elias.oreoEssentials.jails.internal.jail.JailService(plugin, jailStorage);
        this.jailService.enable();
        plugin.getServer().getPluginManager().registerEvents(new fr.elias.oreoEssentials.jails.internal.jail.JailGuardListener(jailService), plugin);
        plugin.getServer().getPluginManager().registerEvents(new fr.elias.oreoEssentials.jails.internal.jail.JailJoinListener(jailService), plugin);

        services(ConfigurationServices.class).getCommands().registerLegacy("jail",
                new fr.elias.oreoEssentials.jails.internal.jail.commands.JailCommand(jailService),
                new fr.elias.oreoEssentials.jails.internal.jail.commands.JailCommandTabCompleter(jailService));
        services(ConfigurationServices.class).getCommands().registerLegacy("jailedit",
                new fr.elias.oreoEssentials.jails.internal.jail.commands.JailEditCommand(jailService),
                new fr.elias.oreoEssentials.jails.internal.jail.commands.JailEditCommandTabCompleter(jailService));
        services(ConfigurationServices.class).getCommands().registerLegacy("jaillist",
                new fr.elias.oreoEssentials.jails.internal.jail.commands.JailListCommand(jailService),
                new fr.elias.oreoEssentials.jails.internal.jail.commands.JailListCommandTabCompleter(jailService));

        if (services(StorageServices.class).getPlayerWarpService() != null) {
            services(ConfigurationServices.class).getCommands().register(new PlayerWarpCommand(services(StorageServices.class).getPlayerWarpService()));
            PlayerWarpWhitelistCommand pwwCmd = new PlayerWarpWhitelistCommand(services(StorageServices.class).getPlayerWarpService());
            services(ConfigurationServices.class).getCommands().registerLegacy("pwwhitelist", pwwCmd, pwwCmd);
            plugin.getLogger().info("[PlayerWarps] /pwwhitelist registered.");
        }

        plugin.getLogger().info("[Jails] System initialized with " + jailStorage.getClass().getSimpleName());
    }
}
