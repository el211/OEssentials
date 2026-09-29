package fr.elias.oreoEssentials.configuration.lifecycle;

import com.google.gson.Gson;
import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.platform.commands.CommandManager;
import fr.elias.oreoEssentials.configuration.internal.config.ConfigService;
import fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig;
import fr.elias.oreoEssentials.configuration.internal.autoreboot.AutoRebootService;
import fr.elias.oreoEssentials.crafting.internal.customcraft.CraftActionsConfig;
import fr.elias.oreoEssentials.configuration.internal.invlook.manager.InvlookManager;
import fr.elias.oreoEssentials.configuration.internal.services.FreezeService;
import fr.elias.oreoEssentials.configuration.internal.logging.KillallLogger;

@ModuleApi("services")
public interface ConfigurationServices {
    fr.elias.oreoEssentials.configuration.internal.config.SettingsConfig getSettingsConfig();
    fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyService getTempFlyService();
    fr.elias.oreoEssentials.configuration.internal.tempfly.TempFlyConfig getTempFlyConfig();
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
    fr.elias.oreoEssentials.configuration.internal.config.CrossServerSettings getCrossServerSettings();
    Gson getGson();
}
