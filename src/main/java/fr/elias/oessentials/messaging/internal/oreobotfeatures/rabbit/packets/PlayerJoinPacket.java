package fr.elias.oessentials.messaging.internal.oreobotfeatures.rabbit.packets;


import fr.elias.oessentials.messaging.internal.rabbitmq.packet.Packet;
import fr.elias.oessentials.messaging.internal.rabbitmq.stream.FriendlyByteInputStream;
import fr.elias.oessentials.messaging.internal.rabbitmq.stream.FriendlyByteOutputStream;
import java.util.UUID;

public class PlayerJoinPacket extends Packet {

    private UUID playerId;
    private String playerName;

    public PlayerJoinPacket() {
        this.playerId = null;
        this.playerName = null;
    }

    public PlayerJoinPacket(UUID playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    protected void read(FriendlyByteInputStream stream) {
        this.playerId = stream.readUUID();
        this.playerName = stream.readString();
    }

    @Override
    protected void write(FriendlyByteOutputStream stream) {
        stream.writeUUID(playerId);
        stream.writeString(playerName);
    }
}
