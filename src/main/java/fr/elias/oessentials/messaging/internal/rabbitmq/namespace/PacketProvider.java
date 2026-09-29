package fr.elias.oessentials.messaging.internal.rabbitmq.namespace;

import fr.elias.oessentials.messaging.internal.rabbitmq.packet.Packet;

@FunctionalInterface
public interface PacketProvider<T extends Packet> {
    T createPacket();
}
