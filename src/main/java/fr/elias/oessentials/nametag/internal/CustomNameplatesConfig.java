package fr.elias.oessentials.nametag.internal;

import fr.elias.oessentials.OEssentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Loads and reloads plugins/OEssentials/custom-nameplates/config.yml.
 * On first run the default file is copied from the jar's resources.
 */
public final class CustomNameplatesConfig {

    private final OEssentials plugin;
    private File file;
    private FileConfiguration cfg;

    private static final String RESOURCE_PATH = "custom-nameplates/config.yml";

    public CustomNameplatesConfig(OEssentials plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        File folder = new File(plugin.getDataFolder(), "custom-nameplates");
        if (!folder.exists()) folder.mkdirs();

        file = new File(folder, "config.yml");
        if (!file.exists()) {
            plugin.saveResource(RESOURCE_PATH, false);
        }

        cfg = YamlConfiguration.loadConfiguration(file);
    }

    /** Returns the raw FileConfiguration for this config file. */
    public FileConfiguration raw() {
        return cfg;
    }

    /** Saves any in-memory changes back to the config file on disk. */
    public void save() {
        try {
            cfg.save(file);
        } catch (java.io.IOException e) {
            plugin.getLogger().warning("[CustomNameplates] Failed to save config: " + e.getMessage());
        }
    }
}
