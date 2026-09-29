package fr.elias.oreoEssentials.inventory.internal.auctionhouse.commands;

import fr.elias.oreoEssentials.inventory.internal.auctionhouse.AuctionHouseModule;
import fr.elias.oreoEssentials.inventory.internal.auctionhouse.gui.BrowseGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SearchCommand implements CommandExecutor {

    private final AuctionHouseModule module;

    public SearchCommand(AuctionHouseModule module) {
        this.module = module;
    }

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

        if (args.length == 0) {
            p.sendMessage("§eUsage: §6/" + label + " <keyword...>");
            p.sendMessage("§7Example: §f/" + label + " diamond sword");
            return true;
        }

        String query = String.join(" ", args).trim();
        if (query.length() < 2) {
            p.sendMessage("§cPlease type at least 2 characters.");
            return true;
        }

        BrowseGUI.getInventory(module, query).open(p);
        return true;
    }
}
