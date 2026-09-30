package fr.elias.oessentials.platform.modularity;

import dev.oreo.modulith.core.MinecraftModule;
import dev.oreo.modulith.paper.PaperModulith;
import fr.elias.oessentials.OEssentials;
import java.util.List;

public final class OreoModules {
    private OreoModules() {}
    public static final List<Class<? extends MinecraftModule>> TYPES = List.of(
        fr.elias.oessentials.configuration.lifecycle.ConfigurationModule.class,
        fr.elias.oessentials.guards.lifecycle.PerformanceGuardsModule.class,
        fr.elias.oessentials.storage.lifecycle.StorageModule.class,
        fr.elias.oessentials.redis.lifecycle.RedisModule.class,
        fr.elias.oessentials.economy.lifecycle.EconomyModule.class,
        fr.elias.oessentials.commandcontrol.lifecycle.CommandControlModule.class,
        fr.elias.oessentials.skins.lifecycle.SkinsModule.class,
        fr.elias.oessentials.commandtoggle.lifecycle.CommandToggleModule.class,
        fr.elias.oessentials.kits.lifecycle.KitsModule.class,
        fr.elias.oessentials.proxy.lifecycle.ProxyMessagingModule.class,
        fr.elias.oessentials.afk.lifecycle.AfkModule.class,
        fr.elias.oessentials.maintenance.lifecycle.MaintenanceModule.class,
        fr.elias.oessentials.help.lifecycle.HelpModule.class,
        fr.elias.oessentials.ignore.lifecycle.IgnoreModule.class,
        fr.elias.oessentials.warnings.lifecycle.WarningsModule.class,
        fr.elias.oessentials.punishment.lifecycle.PunishmentLoggerModule.class,
        fr.elias.oessentials.motd.lifecycle.MotdModule.class,
        fr.elias.oessentials.rules.lifecycle.RulesModule.class,
        fr.elias.oessentials.mail.lifecycle.MailModule.class,
        fr.elias.oessentials.inventory.lifecycle.InventoriesModule.class,
        fr.elias.oessentials.modgui.lifecycle.ModGuiModule.class,
        fr.elias.oessentials.notes.lifecycle.NotesModule.class,
        fr.elias.oessentials.trade.lifecycle.TradeModule.class,
        fr.elias.oessentials.crafting.lifecycle.CustomCraftingModule.class,
        fr.elias.oessentials.freeze.lifecycle.FreezeModule.class,
        fr.elias.oessentials.daily.lifecycle.DailyModule.class,
        fr.elias.oessentials.mute.lifecycle.MuteSystemModule.class,
        fr.elias.oessentials.discord.lifecycle.DiscordIntegrationModule.class,
        fr.elias.oessentials.mobs.lifecycle.MobsModule.class,
        fr.elias.oessentials.clearlag.lifecycle.ClearLagModule.class,
        fr.elias.oessentials.shop.lifecycle.ShopModule.class,
        fr.elias.oessentials.chat.lifecycle.ChatModule.class,
        fr.elias.oessentials.enderchest.lifecycle.EnderChestModule.class,
        fr.elias.oessentials.sync.lifecycle.InventorySyncModule.class,
        fr.elias.oessentials.player.lifecycle.PlayerServicesModule.class,
        fr.elias.oessentials.dialogs.lifecycle.DialogsModule.class,
        fr.elias.oessentials.messaging.lifecycle.MessagingModule.class,
        fr.elias.oessentials.currency.lifecycle.CurrencySystemModule.class,
        fr.elias.oessentials.worlds.lifecycle.CustomWorldsModule.class,
        fr.elias.oessentials.commands.lifecycle.CommandsModule.class,
        fr.elias.oessentials.jails.lifecycle.JailsModule.class,
        fr.elias.oessentials.portals.lifecycle.PortalsModule.class,
        fr.elias.oessentials.grouprtp.lifecycle.GroupRtpModule.class,
        fr.elias.oessentials.jumppads.lifecycle.JumpPadsModule.class,
        fr.elias.oessentials.playervaults.lifecycle.PlayerVaultsModule.class,
        fr.elias.oessentials.rtp.lifecycle.RtpModule.class,
        fr.elias.oessentials.bossbar.lifecycle.BossBarModule.class,
        fr.elias.oessentials.scoreboard.lifecycle.ScoreboardModule.class,
        fr.elias.oessentials.tab.lifecycle.TabModule.class,
        fr.elias.oessentials.holograms.lifecycle.HologramsModule.class,
        fr.elias.oessentials.shards.lifecycle.ShardingModule.class,
        fr.elias.oessentials.playtime.lifecycle.PlaytimeModule.class,
        fr.elias.oessentials.webpanel.lifecycle.WebPanelModule.class,
        fr.elias.oessentials.events.lifecycle.EventsModule.class,
        fr.elias.oessentials.nametag.lifecycle.NametagModule.class,
        fr.elias.oessentials.integrations.lifecycle.IntegrationsModule.class
    );

    public static PaperModulith start(OEssentials plugin) {
        var builder = PaperModulith.builder(plugin);
        TYPES.forEach(builder::module);
        return builder.start();
    }
}
