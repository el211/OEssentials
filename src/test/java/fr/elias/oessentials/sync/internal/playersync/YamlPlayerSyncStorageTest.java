package fr.elias.oessentials.sync.internal.playersync;

import fr.elias.oessentials.OEssentials;
import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class YamlPlayerSyncStorageTest {
    @TempDir Path directory;
    private OEssentials plugin;

    @BeforeEach
    void setup() {
        plugin = mock(OEssentials.class);
        when(plugin.getDataFolder()).thenReturn(directory.toFile());
    }

    @Test
    void freshStorageHasNoSnapshotAndCreatesFileOnFirstSave() throws Exception {
        var storage = new YamlPlayerSyncStorage(plugin);
        UUID player = UUID.randomUUID();
        assertNull(storage.load(player));
        assertNull(storage.load(UUID.randomUUID()));
        assertFalse(Files.exists(directory.resolve("config/player-sync.yml")));

        var snapshot = new PlayerSyncSnapshot();
        snapshot.level = 12;
        snapshot.exp = 0.5f;
        snapshot.health = 18;
        storage.save(player, snapshot);

        var reopened = new YamlPlayerSyncStorage(plugin);
        var loaded = reopened.load(player);
        assertNotNull(loaded);
        assertEquals(12, loaded.level);
        assertEquals(0.5f, loaded.exp);
        assertEquals(18, loaded.health);
        assertNull(reopened.load(UUID.randomUUID()));
    }

    @Test
    void loadsSnapshotsWrittenAfterAnInitiallyMissingFile() throws Exception {
        var reader = new YamlPlayerSyncStorage(plugin);
        UUID player = UUID.randomUUID();
        assertNull(reader.load(player));

        var snapshot = new PlayerSyncSnapshot();
        snapshot.level = 7;
        new YamlPlayerSyncStorage(plugin).save(player, snapshot);
        assertEquals(7, reader.load(player).level);
    }

    @Test
    void malformedExistingFileStillReportsAnError() throws Exception {
        var storage = new YamlPlayerSyncStorage(plugin);
        Path file = directory.resolve("config/player-sync.yml");
        Files.createDirectories(file.getParent());
        Files.writeString(file, "players: [unterminated");
        assertThrows(InvalidConfigurationException.class, () -> storage.load(UUID.randomUUID()));
    }
}
