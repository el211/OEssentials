package fr.elias.oreoEssentials.configuration.internal.autoreboot.rabbit;

import fr.elias.oreoEssentials.messaging.internal.rabbitmq.namespace.PacketNamespace;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.impl.tp.SafeZoneEnterPacket;

public final class RebootPacketNamespace extends PacketNamespace {

    public static final int SAFE_ZONE_ENTER_ID = 2101;

    public RebootPacketNamespace() {
        super((short) 21);
    }

    @Override
    protected void registerPackets() {
        registerPacket(SAFE_ZONE_ENTER_ID, SafeZoneEnterPacket.class, SafeZoneEnterPacket::new);
    }
}
