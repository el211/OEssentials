package fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.event;



import fr.elias.oreoEssentials.messaging.internal.rabbitmq.channel.PacketChannel;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.Packet;

public interface PacketSubscriber<T extends Packet> {

    void onReceive(PacketChannel channel, T packet);
}

