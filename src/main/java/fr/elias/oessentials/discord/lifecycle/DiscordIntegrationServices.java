package fr.elias.oessentials.discord.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface DiscordIntegrationServices {
    fr.elias.oessentials.integrations.internal.integration.DiscordModerationNotifier getDiscordMod();
    fr.elias.oessentials.discord.internal.discordbot.DiscordLinkManager getDiscordLinkManager();
}
