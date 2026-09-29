package fr.elias.oessentials.notes.internal.notes;

import fr.elias.oessentials.OEssentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlayerNotesManager {

    private final OEssentials plugin;
    private final File file;
    private FileConfiguration cfg;

    public PlayerNotesManager(OEssentials plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "data/notes.yml");
        initializeFile();
        reload();
    }

    private void initializeFile() {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Failed to create notes.yml: " + e.getMessage());
            }
        }
    }

    public void reload() {
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    public List<String> getNotes(UUID player) {
        List<String> notes = cfg.getStringList("players." + player.toString());
        return new ArrayList<>(notes);
    }

    public void addNote(UUID target, String staffName, String text) {
        List<String> list = getNotes(target);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String note = timestamp + " | " + staffName + " | " + text;
        list.add(note);
        cfg.set("players." + target.toString(), list);
        save();
    }

    private void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save notes.yml: " + e.getMessage());
            e.printStackTrace();
        }
    }
}