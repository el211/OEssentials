package fr.elias.oessentials.configuration.internal.api;

import fr.elias.oessentials.api.IAutoRebootAPI;
import fr.elias.oessentials.configuration.internal.autoreboot.AutoRebootService;

public class AutoRebootAPIImpl implements IAutoRebootAPI {
    private final AutoRebootService svc;
    public AutoRebootAPIImpl(AutoRebootService svc) { this.svc = svc; }

    @Override public void stop() { svc.stop(); }
    @Override public void reload() { svc.reload(); }
}
