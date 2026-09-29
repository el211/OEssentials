package fr.elias.oessentials.inventory.internal.auctionhouse.commands;

import fr.elias.oessentials.inventory.internal.auctionhouse.AuctionHouseModule;
import fr.elias.oessentials.inventory.internal.auctionhouse.gui.SoldGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SoldCommand implements CommandExecutor {

    private final AuctionHouseModule module;

    public SoldCommand(AuctionHouseModule module) { this.module = module; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) { sender.sendMessage("§cPlayers only."); return true; }
        if (!module.enabled()) { p.sendMessage("§cThe auction house is currently disabled."); return true; }
        if (!p.hasPermission("oreo.ah.use")) { p.sendMessage(module.getConfig().getMessage("errors.no-permission")); return true; }
        SoldGUI.getInventory(module).open(p);
        return true;
    }
}