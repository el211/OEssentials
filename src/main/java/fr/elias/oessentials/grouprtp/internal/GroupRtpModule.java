package fr.elias.oessentials.grouprtp.internal;

import fr.elias.oessentials.OEssentials;
import fr.elias.oessentials.grouprtp.internal.command.GroupRtpCommand;
import fr.elias.oessentials.grouprtp.internal.listener.GroupRtpListener;
import fr.elias.oessentials.grouprtp.internal.listener.GroupRtpWandListener;
import fr.elias.oessentials.grouprtp.internal.service.GroupRtpService;
import fr.elias.oessentials.platform.scheduling.OreScheduler;

/**
 * Entry point for the Group RTP Portal module.
 *
 * <p>Initialised from {@code OEssentials.initGroupRtp()}.  Owns the
 * config, service, listeners, and ambient-particle ticker.</p>
 */
public final class GroupRtpModule {

    private final OEssentials plugin;
    private final GroupRtpConfig  config;
    private final GroupRtpService service;

    public GroupRtpModule(OEssentials plugin) {
        this.plugin  = plugin;
        this.config  = new GroupRtpConfig(plugin);
        this.service = new GroupRtpService(plugin, config);
    }

    /** Called once from OEssentials.initGroupRtp(). */
    public void init() {
        if (!config.isEnabled()) {
            plugin.getLogger().info("[GroupRTP] Disabled by config.");
            return;
        }

        // Register listeners
        var mainListener = new GroupRtpListener(service);
        var cmd          = new GroupRtpCommand(this);
        var wandListener = new GroupRtpWandListener(cmd);

        plugin.getServer().getPluginManager().registerEvents(mainListener, plugin);
        plugin.getServer().getPluginManager().registerEvents(wandListener, plugin);

        // Register command
        plugin.getCommands().register(cmd);

        // Ambient particle ticker — every 10 ticks (~0.5 s)
        OreScheduler.runTimer(plugin, service::tickAmbient, 20L, 10L);

        plugin.getLogger().info("[GroupRTP] Initialized with "
                + config.getPortals().size() + " portal(s).");
    }

    public GroupRtpConfig  getConfig()  { return config; }
    public GroupRtpService getService() { return service; }
}
