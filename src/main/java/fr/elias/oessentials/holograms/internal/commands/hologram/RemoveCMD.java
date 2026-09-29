package fr.elias.oessentials.holograms.internal.commands.hologram;

import fr.elias.oessentials.holograms.internal.OHolograms;
import fr.elias.oessentials.holograms.internal.api.OHologramsPlugin;
import fr.elias.oessentials.holograms.internal.api.events.HologramDeleteEvent;
import fr.elias.oessentials.holograms.internal.api.hologram.Hologram;
import fr.elias.oessentials.holograms.internal.commands.Subcommand;
import fr.elias.oessentials.platform.scheduling.OreScheduler;
import de.oliver.fancylib.MessageHelper;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RemoveCMD implements Subcommand {

    @Override
    public List<String> tabcompletion(@NotNull CommandSender player, @Nullable Hologram hologram, @NotNull String[] args) {
        return null;
    }

    @Override
    public boolean run(@NotNull CommandSender player, @Nullable Hologram hologram, @NotNull String[] args) {

        if (!(player.hasPermission("OHolograms.hologram.remove"))) {
            MessageHelper.error(player, "You don't have the required permission to remove a hologram");
            return false;
        }

        if (!new HologramDeleteEvent(hologram, player).callEvent()) {
            MessageHelper.error(player, "Removing the hologram was cancelled");
            return false;
        }

        OreScheduler.run(OHolograms.get().getPlugin(), () -> {
            OHolograms.get().getHologramsManager().removeHologram(hologram);
            MessageHelper.success(player, "Removed the hologram");
        });

        return true;
    }
}
