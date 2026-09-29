package fr.elias.oreoEssentials.platform.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnedCommandsTest {
    @Test
    void removesOwnedAliasesButPreservesAnotherPluginsReplacement() {
        Command own = mock(Command.class);
        Command other = mock(Command.class);
        CommandMap map = mock(CommandMap.class);
        var entries = new HashMap<String, Command>();
        entries.put("home", other);
        entries.put("oreoessentials:home", own);
        entries.put("homes-alias", own);
        entries.put("other:home", other);
        when(map.getKnownCommands()).thenReturn(entries);
        var owned = new OwnedCommands();
        owned.add(own);
        owned.removeFrom(map);
        assertEquals(2, entries.size());
        assertSame(other, entries.get("home"));
        verify(own).unregister(map);
        verify(other, never()).unregister(map);
        owned.removeFrom(map);
        verify(own, times(1)).unregister(map);
    }
}
