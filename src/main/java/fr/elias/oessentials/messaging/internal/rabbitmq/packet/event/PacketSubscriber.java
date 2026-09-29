package fr.elias.oessentials.messaging.internal.rabbitmq.packet.event;



import fr.elias.oessentials.messaging.internal.rabbitmq.channel.PacketChannel;
import fr.elias.oessentials.messaging.internal.rabbitmq.packet.Packet;

public interface PacketSubscriber<T extends Packet> {

    void onReceive(PacketChannel channel, T packet);
}

