package fr.elias.oreoEssentials.trade.internal.rabbit.packet;

import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.Packet;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.stream.FriendlyByteInputStream;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.stream.FriendlyByteOutputStream;

import java.util.UUID;

public final class TradeCancelPacket extends Packet {
    private UUID sessionId;
    private String reason;

    public TradeCancelPacket() { }

    public TradeCancelPacket(UUID sessionId, String reason) {
        this.sessionId = sessionId;
        this.reason = (reason == null ? "" : reason);
    }

    public UUID getSessionId() { return sessionId; }
    public String getReason()  { return reason; }

    @Override
    protected void write(FriendlyByteOutputStream out) {
        out.writeUUID(sessionId);
        out.writeString(reason != null ? reason : "");
    }

    @Override
    protected void read(FriendlyByteInputStream in) {
        sessionId = in.readUUID();
        reason    = in.readString();
    }
}
