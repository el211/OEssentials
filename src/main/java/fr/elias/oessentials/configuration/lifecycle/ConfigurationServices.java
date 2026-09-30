package fr.elias.oessentials.configuration.lifecycle;

import com.google.gson.Gson;
import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.platform.commands.CommandManager;
import fr.elias.oessentials.configuration.internal.config.ConfigService;
import fr.elias.oessentials.configuration.internal.config.SettingsConfig;
import fr.elias.oessentials.configuration.internal.autoreboot.AutoRebootService;
import fr.elias.oessentials.crafting.internal.customcraft.CraftActionsConfig;
import fr.elias.oessentials.configuration.internal.invlook.manager.InvlookManager;
import fr.elias.oessentials.configuration.internal.services.FreezeService;
import fr.elias.oessentials.configuration.internal.logging.KillallLogger;

@ModuleApi("services")
public interface ConfigurationServices {
    fr.elias.oessentials.configuration.internal.config.SettingsConfig getSettingsConfig();
    fr.elias.oessentials.configuration.internal.tempfly.TempFlyService getTempFlyService();
    fr.elias.oessentials.configuration.internal.tempfly.TempFlyConfig getTempFlyConfig();
    ConfigService getConfigService();
    AutoRebootService getAutoRebootService();
    CraftActionsConfig getCraftActionsConfig();
    CommandManager getCommands();
    FreezeService getFreezeService();
    KillallLogger getKillallLogger();
    SettingsConfig getSettings();
    boolean getEconomyEnabled();
    boolean getRedisEnabled();
    boolean getRabbitEnabled();
    InvlookManager getInvlookManager();
    fr.elias.oessentials.configuration.internal.config.CrossServerSettings getCrossServerSettings();
    Gson getGson();
}
