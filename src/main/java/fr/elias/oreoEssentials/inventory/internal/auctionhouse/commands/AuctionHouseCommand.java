package fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands;

import fr.elias.oreoEssentials.inventory.internal.auctionhouse.AuctionHouseModule;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.gui.BrowseGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class AuctionHouseCommand implements CommandExecutor {

    private final AuctionHouseModule module;

    public AuctionHouseCommand(AuctionHouseModule module) { this.module = module; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cThis command can only be used by players.");
            return true;
        }
        if (!module.enabled()) {
            p.sendMessage("§cThe auction house is currently disabled.");
            return true;
        }
        if (!p.hasPermission("oreo.ah.use")) {
            p.sendMessage(module.getConfig().getMessage("errors.no-permission"));
            return true;
        }
        BrowseGUI.getInventory(module).open(p);
        return true;
    }
}