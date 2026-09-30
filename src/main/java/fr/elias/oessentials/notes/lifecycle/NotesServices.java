package fr.elias.oessentials.notes.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.modgui.internal.ip.IpTracker;
import fr.elias.oessentials.notes.internal.notes.NotesChatListener;
import fr.elias.oessentials.notes.internal.notes.PlayerNotesManager;

@ModuleApi("services")
public interface NotesServices {
    PlayerNotesManager getNotesManager();
    NotesChatListener getNotesChat();
    IpTracker getIpTracker();
}
