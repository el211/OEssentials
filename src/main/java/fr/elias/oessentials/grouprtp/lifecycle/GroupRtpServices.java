package fr.elias.oessentials.grouprtp.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface GroupRtpServices {
    fr.elias.oessentials.grouprtp.internal.GroupRtpModule getGroupRtpModule();
}
