package fr.elias.oreoEssentials.platform.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/** Tracks actual command objects so cleanup never removes another plugin's replacement. */
final class OwnedCommands {
    private final Set<Command> commands = Collections.newSetFromMap(new IdentityHashMap<>());

    void add(Command command) { commands.add(command); }

    void removeFrom(CommandMap map) {
        map.getKnownCommands().entrySet().removeIf(entry -> commands.contains(entry.getValue()));
        commands.forEach(command -> command.unregister(map));
        commands.clear();
    }
}
