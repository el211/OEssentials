package fr.elias.oreoEssentials.messaging.internal.rabbitmq;


import fr.elias.oreoEssentials.messaging.internal.rabbitmq.IndividualPacketChannel;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.MultiPacketChannel;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.channel.PacketChannel;


public final class PacketChannels {
    private PacketChannels() {}

    public static final PacketChannel GLOBAL =
            IndividualPacketChannel.create("global");

    public static PacketChannel individual(String channel) {
        return IndividualPacketChannel.create(channel);
    }

    public static PacketChannel multiple(String... channels) {
        return MultiPacketChannel.create(channels);
    }

    public static PacketChannel multiple(PacketChannel... channels) {
        return MultiPacketChannel.create(channels);
    }
}

