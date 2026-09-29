package fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.impl;


import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.Packet;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.stream.FriendlyByteInputStream;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.stream.FriendlyByteOutputStream;
import java.util.UUID;

public class SendRemoteMessagePacket extends Packet {

    private UUID targetId;
    private String message;

    public SendRemoteMessagePacket() {
        this.targetId = null;
        this.message = null;
    }

    public SendRemoteMessagePacket(UUID targetId, String message) {
        this.targetId = targetId;
        this.message = message;
    }

    public UUID getTargetId() {
        return targetId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    protected void write(FriendlyByteOutputStream stream) {
        stream.writeUUID(targetId);
        stream.writeString(message);
    }

    @Override
    protected void read(FriendlyByteInputStream stream) {
        this.targetId = stream.readUUID();
        this.message = stream.readString();
    }
}
