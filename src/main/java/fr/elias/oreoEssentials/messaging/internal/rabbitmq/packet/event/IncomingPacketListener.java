package fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.event;


import fr.elias.oreoEssentials.messaging.internal.rabbitmq.channel.PacketChannel;

public interface IncomingPacketListener {

    void onReceive(PacketChannel channel, byte[] content);

}

