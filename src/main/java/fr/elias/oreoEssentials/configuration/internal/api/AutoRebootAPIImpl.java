package fr.elias.oreoEssentials.configuration.internal.api;

import fr.elias.oreoEssentials.api.IAutoRebootAPI;
import fr.elias.oreoEssentials.configuration.internal.autoreboot.AutoRebootService;

public class AutoRebootAPIImpl implements IAutoRebootAPI {
    private final AutoRebootService svc;
    public AutoRebootAPIImpl(AutoRebootService svc) { this.svc = svc; }

    @Override public void stop() { svc.stop(); }
    @Override public void reload() { svc.reload(); }
}
