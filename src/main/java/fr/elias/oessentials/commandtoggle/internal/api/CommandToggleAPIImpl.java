package fr.elias.oessentials.commandtoggle.internal.api;

import fr.elias.oessentials.api.ICommandToggleAPI;
import fr.elias.oessentials.commandtoggle.internal.CommandToggleService;

public class CommandToggleAPIImpl implements ICommandToggleAPI {
    private final CommandToggleService svc;
    public CommandToggleAPIImpl(CommandToggleService svc) { this.svc = svc; }

    @Override public void applyToggles() { svc.applyToggles(); }
    @Override public void reload() { svc.reload(); }
}
