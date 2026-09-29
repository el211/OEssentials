package fr.elias.oessentials.configuration.internal.migration;

import fr.elias.oessentials.OEssentials;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * One-time migration that moves scoreboard and tab configs into
 * plugins/OEssentials/scoreboard-tab/.
 *
 * Migration logic (runs on every startup, but is a no-op once already done):
 *
 *  scoreboard.yml:
 *   - If scoreboard-tab/scoreboard.yml already exists  → skip (already migrated)
 *   - Else if config.yml has a "scoreboard:" section   → copy it to scoreboard-tab/scoreboard.yml
 *                                                         then remove the section from config.yml
 *   - Else                                             → extract default from jar
 *
 *  tab.yml:
 *   - If scoreboard-tab/tab.yml already exists         → skip (already migrated)
 *   - Else if old tab.yml exists at the plugin root    → move it to scoreboard-tab/tab.yml
 *   - Else                                             → extract default from jar
 */
public final class ScoreboardTabMigrator {

    private ScoreboardTabMigrator() {}

    public static void migrate(OEssentials plugin) {
        File folder = new File(plugin.getDataFolder(), "config/scoreboard-tab");
        if (!folder.exists()) folder.mkdirs();

        migrateScoreboard(plugin, folder);
        migrateTab(plugin, folder);
    }

    // ── Scoreboard ────────────────────────────────────────────────────────────

    private static void migrateScoreboard(OEssentials plugin, File folder) {
        File dest = new File(folder, "scoreboard.yml");
        if (dest.exists()) return; // already migrated

        FileConfiguration mainConfig = plugin.getConfig();
        ConfigurationSection sbSection = mainConfig.getConfigurationSection("scoreboard");

        if (sbSection != null) {
            // User has an existing scoreboard section — preserve their config
            YamlConfiguration out = new YamlConfiguration();
            copySection(sbSection, out, "scoreboard");
            try {
                out.save(dest);
                // Remove from config.yml
                mainConfig.set("scoreboard", null);
                plugin.saveConfig();
                plugin.getLogger().info("[Migration] Scoreboard config moved to scoreboard-tab/scoreboard.yml");
            } catch (IOException e) {
                plugin.getLogger().warning("[Migration] Could not save scoreboard.yml: " + e.getMessage());
            }
        } else {
            // No existing config — extract the bundled default
            plugin.saveResource("config/scoreboard-tab/scoreboard.yml", false);
            plugin.getLogger().info("[Migration] Created default scoreboard-tab/scoreboard.yml");
        }
    }

    // ── Tab ───────────────────────────────────────────────────────────────────

    private static void migrateTab(OEssentials plugin, File folder) {
        File dest = new File(folder, "tab.yml");
        if (dest.exists()) return; // already migrated

        File oldTab = new File(plugin.getDataFolder(), "tab.yml");
        if (oldTab.exists()) {
            try {
                Files.copy(oldTab.toPath(), dest.toPath());
                oldTab.delete();
                plugin.getLogger().info("[Migration] Tab config moved to scoreboard-tab/tab.yml");
            } catch (IOException e) {
                plugin.getLogger().warning("[Migration] Could not migrate tab.yml: " + e.getMessage());
            }
        } else {
            plugin.saveResource("config/scoreboard-tab/tab.yml", false);
            plugin.getLogger().info("[Migration] Created default scoreboard-tab/tab.yml");
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /**
     * Recursively copies all keys from {@code src} into {@code dest}
     * under the given root prefix.
     */
    private static void copySection(ConfigurationSection src, YamlConfiguration dest, String prefix) {
        for (String key : src.getKeys(false)) {
            String path = prefix + "." + key;
            Object value = src.get(key);
            if (value instanceof ConfigurationSection sub) {
                copySection(sub, dest, path);
            } else {
                dest.set(path, value);
            }
        }
    }
}
