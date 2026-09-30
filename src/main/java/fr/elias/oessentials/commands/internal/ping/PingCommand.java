// File: src/main/java/fr/elias/oessentials/commands/core/playercommands/PingCommand.java
package fr.elias.oessentials.commands.internal.ping;

import fr.elias.oessentials.platform.commands.OreoCommand;
import fr.elias.oessentials.shared.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class PingCommand implements OreoCommand {
    @Override public String name() { return "ping"; }
    @Override public List<String> aliases() { return List.of(); }
    @Override public String permission() { return "oreo.ping"; }
    @Override public String usage() { return ""; }
    @Override public boolean playerOnly() { return true; }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;

        int ping = p.getPing(); // 1.21 API

        Lang.send(p, "ping.self",
                "<green>Your ping: <yellow>%ping%</yellow>ms</green>",
                Map.of("ping", String.valueOf(ping)));

        return true;
    }
}