package fr.elias.oreoEssentials.mail.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationServices;

@PluginModule(value = "mail", dependencies = {"configuration::services", "rules"})
public final class MailModule extends ManagedModule implements MailServices {
    private fr.elias.oreoEssentials.mail.internal.MailService mailService;
    private fr.elias.oreoEssentials.mail.internal.MailListener mailListener;

    @Override
    protected void start() {
        initMail();
    }

    @Override public fr.elias.oreoEssentials.mail.internal.MailService getMailService() { return mailService; }
    @Override public fr.elias.oreoEssentials.mail.internal.MailListener getMailListener() { return mailListener; }

    private void initMail() {
        try {
            this.mailService  = new fr.elias.oreoEssentials.mail.internal.MailService(plugin);
            this.mailListener = new fr.elias.oreoEssentials.mail.internal.MailListener(plugin, this.mailService);
            var mailCmd       = new fr.elias.oreoEssentials.mail.internal.MailCommand(plugin, this.mailService);
            services(ConfigurationServices.class).getCommands().register(mailCmd);
            plugin.getServer().getPluginManager().registerEvents(this.mailListener, plugin);
            plugin.getLogger().info("[Mail] Initialized.");
        } catch (Throwable t) {
            plugin.getLogger().severe("[Mail] Failed to initialize: " + t.getMessage());
        }
    }
}
