package fr.elias.oreoEssentials.messaging.internal.rabbitmq.namespace;

public final class DynamicPacketNamespace extends PacketNamespace {

    public static final short ID = (short) 32000;

    public DynamicPacketNamespace() {
        super(ID);
    }

    @Override
    protected void registerPackets() {
    }
}
