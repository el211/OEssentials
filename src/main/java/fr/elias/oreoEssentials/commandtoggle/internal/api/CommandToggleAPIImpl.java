package fr.elias.oreoEssentials.commandtoggle.internal.api;

import fr.elias.oreoEssentials.api.ICommandToggleAPI;
import fr.elias.oreoEssentials.commandtoggle.internal.CommandToggleService;

public class CommandToggleAPIImpl implements ICommandToggleAPI {
    private final CommandToggleService svc;
    public CommandToggleAPIImpl(CommandToggleService svc) { this.svc = svc; }

    @Override public void applyToggles() { svc.applyToggles(); }
    @Override public void reload() { svc.reload(); }
}
