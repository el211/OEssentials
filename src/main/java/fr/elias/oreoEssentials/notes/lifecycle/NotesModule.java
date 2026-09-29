package fr.elias.oreoEssentials.notes.lifecycle;

import dev.oreo.modulith.core.PluginModule;
import fr.elias.oreoEssentials.platform.modularity.ManagedModule;
import fr.elias.oreoEssentials.modgui.internal.ip.IpTracker;
import fr.elias.oreoEssentials.notes.internal.notes.NotesChatListener;
import fr.elias.oreoEssentials.notes.internal.notes.PlayerNotesManager;

@PluginModule(value = "notes", dependencies = {"mod-gui"})
public final class NotesModule extends ManagedModule implements NotesServices {
    private PlayerNotesManager notesManager;
    private NotesChatListener notesChat;
    private IpTracker ipTracker;

    @Override
    protected void start() {
        initNotes();
    }

    @Override public PlayerNotesManager getNotesManager() { return notesManager; }
    @Override public NotesChatListener getNotesChat() { return notesChat; }
    @Override public IpTracker getIpTracker() { return ipTracker; }

    private void initNotes() {
        this.notesManager = new PlayerNotesManager(plugin);
        this.notesChat    = new NotesChatListener(plugin, notesManager);
        this.ipTracker    = new IpTracker(plugin);
    }
}
