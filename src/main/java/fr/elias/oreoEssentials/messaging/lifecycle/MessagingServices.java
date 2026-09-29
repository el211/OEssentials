package fr.elias.oreoEssentials.messaging.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.player.internal.back.BackLocation;
import fr.elias.oreoEssentials.player.internal.back.rabbit.BackBroker;
import fr.elias.oreoEssentials.messaging.internal.cross.ModBridge;
import fr.elias.oreoEssentials.messaging.internal.invsee.InvseeService;
import fr.elias.oreoEssentials.messaging.internal.invsee.rabbit.InvseeCrossServerBroker;
import fr.elias.oreoEssentials.player.internal.tp.rabbit.brokers.TpCrossServerBroker;
import fr.elias.oreoEssentials.player.internal.tp.rabbit.brokers.TpaCrossServerBroker;
import fr.elias.oreoEssentials.trade.internal.rabbit.TradeCrossServerBroker;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.PacketManager;
import java.util.Map;
import java.util.UUID;

@ModuleApi("services")
public interface MessagingServices {
    TpCrossServerBroker getTpBroker();
    BackBroker getBackBroker();
    fr.elias.oreoEssentials.messaging.internal.network.NetworkCountReceiver getNetworkCountReceiver();
    TpaCrossServerBroker getTpaBroker();
    ModBridge getModBridge();
    InvseeService getInvseeService();
    InvseeCrossServerBroker getInvseeBroker();
    TradeCrossServerBroker getTradeBroker();
    PacketManager getPacketManager();
    fr.elias.oreoEssentials.storage.internal.homes.HomeTeleportBroker getHomeTpBroker();
    Map<UUID, BackLocation> getPendingBackTeleports();
}
