package fr.elias.oreoEssentials.platform.modularity;

import dev.oreo.modulith.core.MinecraftModule;
import dev.oreo.modulith.paper.PaperModulith;
import fr.elias.oreoEssentials.OreoEssentials;
import java.util.List;

public final class OreoModules {
    private OreoModules() {}
    public static final List<Class<? extends MinecraftModule>> TYPES = List.of(
        fr.elias.oreoEssentials.configuration.lifecycle.ConfigurationModule.class,
        fr.elias.oreoEssentials.guards.lifecycle.PerformanceGuardsModule.class,
        fr.elias.oreoEssentials.storage.lifecycle.StorageModule.class,
        fr.elias.oreoEssentials.redis.lifecycle.RedisModule.class,
        fr.elias.oreoEssentials.economy.lifecycle.EconomyModule.class,
        fr.elias.oreoEssentials.commandcontrol.lifecycle.CommandControlModule.class,
        fr.elias.oreoEssentials.skins.lifecycle.SkinsModule.class,
        fr.elias.oreoEssentials.commandtoggle.lifecycle.CommandToggleModule.class,
        fr.elias.oreoEssentials.kits.lifecycle.KitsModule.class,
        fr.elias.oreoEssentials.proxy.lifecycle.ProxyMessagingModule.class,
        fr.elias.oreoEssentials.afk.lifecycle.AfkModule.class,
        fr.elias.oreoEssentials.maintenance.lifecycle.MaintenanceModule.class,
        fr.elias.oreoEssentials.help.lifecycle.HelpModule.class,
        fr.elias.oreoEssentials.ignore.lifecycle.IgnoreModule.class,
        fr.elias.oreoEssentials.warnings.lifecycle.WarningsModule.class,
        fr.elias.oreoEssentials.punishment.lifecycle.PunishmentLoggerModule.class,
        fr.elias.oreoEssentials.motd.lifecycle.MotdModule.class,
        fr.elias.oreoEssentials.rules.lifecycle.RulesModule.class,
        fr.elias.oreoEssentials.mail.lifecycle.MailModule.class,
        fr.elias.oreoEssentials.inventory.lifecycle.InventoriesModule.class,
        fr.elias.oreoEssentials.modgui.lifecycle.ModGuiModule.class,
        fr.elias.oreoEssentials.notes.lifecycle.NotesModule.class,
        fr.elias.oreoEssentials.trade.lifecycle.TradeModule.class,
        fr.elias.oreoEssentials.crafting.lifecycle.CustomCraftingModule.class,
        fr.elias.oreoEssentials.freeze.lifecycle.FreezeModule.class,
        fr.elias.oreoEssentials.daily.lifecycle.DailyModule.class,
        fr.elias.oreoEssentials.mute.lifecycle.MuteSystemModule.class,
        fr.elias.oreoEssentials.discord.lifecycle.DiscordIntegrationModule.class,
        fr.elias.oreoEssentials.mobs.lifecycle.MobsModule.class,
        fr.elias.oreoEssentials.clearlag.lifecycle.ClearLagModule.class,
        fr.elias.oreoEssentials.shop.lifecycle.ShopModule.class,
        fr.elias.oreoEssentials.chat.lifecycle.ChatModule.class,
        fr.elias.oreoEssentials.enderchest.lifecycle.EnderChestModule.class,
        fr.elias.oreoEssentials.sync.lifecycle.InventorySyncModule.class,
        fr.elias.oreoEssentials.player.lifecycle.PlayerServicesModule.class,
        fr.elias.oreoEssentials.dialogs.lifecycle.DialogsModule.class,
        fr.elias.oreoEssentials.messaging.lifecycle.MessagingModule.class,
        fr.elias.oreoEssentials.currency.lifecycle.CurrencySystemModule.class,
        fr.elias.oreoEssentials.worlds.lifecycle.CustomWorldsModule.class,
        fr.elias.oreoEssentials.commands.lifecycle.CommandsModule.class,
        fr.elias.oreoEssentials.jails.lifecycle.JailsModule.class,
        fr.elias.oreoEssentials.portals.lifecycle.PortalsModule.class,
        fr.elias.oreoEssentials.grouprtp.lifecycle.GroupRtpModule.class,
        fr.elias.oreoEssentials.jumppads.lifecycle.JumpPadsModule.class,
        fr.elias.oreoEssentials.playervaults.lifecycle.PlayerVaultsModule.class,
        fr.elias.oreoEssentials.rtp.lifecycle.RtpModule.class,
        fr.elias.oreoEssentials.bossbar.lifecycle.BossBarModule.class,
        fr.elias.oreoEssentials.scoreboard.lifecycle.ScoreboardModule.class,
        fr.elias.oreoEssentials.tab.lifecycle.TabModule.class,
        fr.elias.oreoEssentials.holograms.lifecycle.HologramsModule.class,
        fr.elias.oreoEssentials.shards.lifecycle.ShardingModule.class,
        fr.elias.oreoEssentials.playtime.lifecycle.PlaytimeModule.class,
        fr.elias.oreoEssentials.webpanel.lifecycle.WebPanelModule.class,
        fr.elias.oreoEssentials.events.lifecycle.EventsModule.class,
        fr.elias.oreoEssentials.nametag.lifecycle.NametagModule.class,
        fr.elias.oreoEssentials.integrations.lifecycle.IntegrationsModule.class
    );

    public static PaperModulith start(OreoEssentials plugin) {
        var builder = PaperModulith.builder(plugin);
        TYPES.forEach(builder::module);
        return builder.start();
    }
}
