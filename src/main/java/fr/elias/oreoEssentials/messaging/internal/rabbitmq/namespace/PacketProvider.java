package fr.elias.oreoEssentials.messaging.internal.rabbitmq.namespace;

import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.Packet;

@FunctionalInterface
public interface PacketProvider<T extends Packet> {
    T createPacket();
}
