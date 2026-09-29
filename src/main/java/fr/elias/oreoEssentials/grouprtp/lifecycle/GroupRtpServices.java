package fr.elias.oreoEssentials.grouprtp.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface GroupRtpServices {
    fr.elias.oreoEssentials.grouprtp.internal.GroupRtpModule getGroupRtpModule();
}
