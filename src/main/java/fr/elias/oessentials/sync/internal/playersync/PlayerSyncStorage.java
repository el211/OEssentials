package fr.elias.oessentials.sync.internal.playersync;

import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface PlayerSyncStorage {
    void save(UUID uuid, PlayerSyncSnapshot snap) throws Exception;
    PlayerSyncSnapshot load(UUID uuid) throws Exception;
}
