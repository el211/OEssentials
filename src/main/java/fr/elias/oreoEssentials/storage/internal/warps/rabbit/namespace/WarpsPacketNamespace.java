package fr.elias.oreoEssentials.storage.internal.warps.rabbit.namespace;

import fr.elias.oreoEssentials.messaging.internal.rabbitmq.namespace.PacketNamespace;
import fr.elias.oreoEssentials.storage.internal.warps.rabbit.packets.WarpTeleportRequestPacket;

public final class WarpsPacketNamespace extends PacketNamespace {

    public static final int WARP_TP_REQ_ID = 1004;

    public WarpsPacketNamespace() {
        super((short) 11);
    }

    @Override
    protected void registerPackets() {
        registerPacket(WARP_TP_REQ_ID, WarpTeleportRequestPacket.class, WarpTeleportRequestPacket::new);
    }
}
