package fr.elias.oessentials.nametag.internal.api;

import fr.elias.oessentials.api.INametagAPI;
import fr.elias.oessentials.nametag.internal.PlayerNametagManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NametagAPIImpl implements INametagAPI {

    private final PlayerNametagManager manager;

    public NametagAPIImpl(PlayerNametagManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean isEnabled() {
        return manager.isEnabled();
    }

    @Override
    public void updateNametag(@NotNull Player player) {
        manager.updateNametag(player);
    }

    @Override
    public void forceUpdate(@NotNull Player player) {
        manager.forceUpdate(player);
    }

    @Override
    public void forceUpdateAll() {
        manager.forceUpdateAll();
    }

    @Override
    public void disableNametag(@NotNull Player player) {
        manager.disableNametag(player);
    }
}
