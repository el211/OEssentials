package fr.elias.oessentials.sync.internal.playersync;

import fr.elias.oessentials.OEssentials;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public final class YamlPlayerSyncStorage implements PlayerSyncStorage {
    private final OEssentials plugin;
    private final File file;
    private YamlConfiguration cfg;

    public YamlPlayerSyncStorage(OEssentials plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "player-sync.yml");
        this.cfg  = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public synchronized void save(UUID uuid, PlayerSyncSnapshot snap) throws Exception {
        cfg.set("players." + uuid + ".blob", PlayerSyncSnapshot.toBase64(snap));
        cfg.save(file);
    }

    @Override
    public synchronized PlayerSyncSnapshot load(UUID uuid) throws Exception {
        cfg.load(file); // reload from disk to avoid stale in-memory cache
        String b64 = cfg.getString("players." + uuid + ".blob", null);
        if (b64 == null) return null;
        return PlayerSyncSnapshot.fromBase64(b64);
    }
}
