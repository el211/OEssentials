package fr.elias.oreoEssentials.discord.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface DiscordIntegrationServices {
    fr.elias.oreoEssentials.integrations.internal.integration.DiscordModerationNotifier getDiscordMod();
    fr.elias.oreoEssentials.discord.internal.discordbot.DiscordLinkManager getDiscordLinkManager();
}
