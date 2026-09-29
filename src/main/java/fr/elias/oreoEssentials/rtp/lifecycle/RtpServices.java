package fr.elias.oreoEssentials.rtp.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.rtp.internal.RtpConfig;
import fr.elias.oreoEssentials.rtp.internal.RtpPendingService;

@ModuleApi("services")
public interface RtpServices {
    fr.elias.oreoEssentials.rtp.internal.RtpCrossServerBridge getRtpBridge();
    RtpPendingService getRtpPendingService();
    RtpConfig getRtpConfig();
    java.util.Map<java.util.UUID, Long> getRtpCooldownCache();
}
