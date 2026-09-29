package fr.elias.oreoEssentials.notes.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.modgui.internal.ip.IpTracker;
import fr.elias.oreoEssentials.notes.internal.notes.NotesChatListener;
import fr.elias.oreoEssentials.notes.internal.notes.PlayerNotesManager;

@ModuleApi("services")
public interface NotesServices {
    PlayerNotesManager getNotesManager();
    NotesChatListener getNotesChat();
    IpTracker getIpTracker();
}
