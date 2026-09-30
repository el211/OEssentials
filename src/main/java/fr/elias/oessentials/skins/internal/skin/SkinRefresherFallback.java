package fr.elias.oessentials.skins.internal.skin;

import fr.elias.oessentials.OEssentials;
import fr.elias.oessentials.platform.scheduling.OreScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

final class SkinRefresherFallback implements SkinRefresher {
    @Override
    public void refresh(Player player) {
        SkinDebug.log("SkinRefresherFallback.refresh called");
        if (player == null || !player.isOnline()) return;
        var plugin = OEssentials.get();

        for (Player viewer : Bukkit.getOnlinePlayers()) {
            if (viewer.equals(player)) continue;
            viewer.hidePlayer(plugin, player);
        }
        OreScheduler.run(plugin, () -> {
            for (Player viewer : Bukkit.getOnlinePlayers()) {
                if (viewer.equals(player)) continue;
                viewer.showPlayer(plugin, player);
            }
        });
        OreScheduler.runLater(plugin, player::updateInventory, 2L);
        SkinDebug.log("Fallback hide/show done");
    }
}
