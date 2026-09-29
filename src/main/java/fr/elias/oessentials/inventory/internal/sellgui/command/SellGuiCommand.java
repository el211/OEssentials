package fr.elias.oessentials.inventory.internal.sellgui.command;

import fr.elias.oessentials.OEssentials;
import fr.elias.oessentials.platform.commands.OreoCommand;
import fr.elias.oessentials.shared.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SellGuiCommand implements OreoCommand {

    private final OEssentials plugin;

    public SellGuiCommand(OEssentials plugin) {
        this.plugin = plugin;
    }

    @Override public String name() { return "sellgui"; }
    @Override public List<String> aliases() { return List.of("sell"); }
    @Override public String permission() { return "oreo.sellgui"; }
    @Override public String usage() { return ""; }
    @Override public boolean playerOnly() { return true; }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;

        if (plugin.getSellGuiManager() == null) {
            Lang.send(sender, "sellgui.not-initialized",
                    "<red>SellGUI is not initialized.</red>");
            return true;
        }

        plugin.getSellGuiManager().openSell(p);
        return true;
    }
}