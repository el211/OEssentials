package fr.elias.oreoEssentials.storage.lifecycle;

import com.mongodb.client.MongoClient;
import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.storage.internal.homes.home.HomeDirectory;
import fr.elias.oreoEssentials.storage.internal.homes.home.HomeService;
import fr.elias.oreoEssentials.storage.internal.playerwarp.PlayerWarpDirectory;
import fr.elias.oreoEssentials.storage.internal.playerwarp.PlayerWarpService;
import fr.elias.oreoEssentials.storage.internal.spawn.SpawnDirectory;
import fr.elias.oreoEssentials.storage.internal.spawn.SpawnService;
import fr.elias.oreoEssentials.storage.internal.warps.WarpService;
import fr.elias.oreoEssentials.storage.internal.warps.rabbit.WarpDirectory;
import fr.elias.oreoEssentials.storage.internal.services.StorageApi;

@ModuleApi("services")
public interface StorageServices {
    MongoClient getHomesMongoClient();
    HomeDirectory getHomeDirectory();
    StorageApi getStorage();
    SpawnService getSpawnService();
    WarpService getWarpService();
    HomeService getHomeService();
    PlayerWarpService getPlayerWarpService();
    PlayerWarpDirectory getPlayerWarpDirectory();
    WarpDirectory getWarpDirectory();
    SpawnDirectory getSpawnDirectory();
    org.bukkit.configuration.file.FileConfiguration getPlayerWarpsConfig();
    fr.elias.oreoEssentials.storage.internal.directory.PlayerDirectory getPlayerDirectory();
    void reloadPlayerWarpsConfig();
}
