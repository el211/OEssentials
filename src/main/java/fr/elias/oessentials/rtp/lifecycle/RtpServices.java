package fr.elias.oessentials.rtp.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.rtp.internal.RtpConfig;
import fr.elias.oessentials.rtp.internal.RtpPendingService;

@ModuleApi("services")
public interface RtpServices {
    fr.elias.oessentials.rtp.internal.RtpCrossServerBridge getRtpBridge();
    RtpPendingService getRtpPendingService();
    RtpConfig getRtpConfig();
    java.util.Map<java.util.UUID, Long> getRtpCooldownCache();
}
