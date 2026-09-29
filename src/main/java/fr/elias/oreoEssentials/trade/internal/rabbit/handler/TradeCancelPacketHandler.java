package fr.elias.oreoEssentials.trade.internal.rabbit.handler;

import fr.elias.oreoEssentials.OreoEssentials;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.channel.PacketChannel;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.event.PacketSubscriber;
import fr.elias.oreoEssentials.trade.internal.rabbit.packet.TradeCancelPacket;

public final class TradeCancelPacketHandler implements PacketSubscriber<TradeCancelPacket> {
    private final OreoEssentials plugin;
    public TradeCancelPacketHandler(OreoEssentials plugin) { this.plugin = plugin; }

    private boolean dbg(){
        try { return OreoEssentials.get().getTradeService().getConfig().debugDeep; }
        catch (Throwable t){ return false; }
    }

    @Override
    public void onReceive(PacketChannel channel, TradeCancelPacket packet) {
        if (dbg()) plugin.getLogger().info("[TRADE] RECV TradeCancelPacket ch=" + channel +
                " session=" + packet.getSessionId() + " reason=\"" + packet.getReason() + "\"");

        if (plugin.getTradeBroker() != null) {
            plugin.getTradeBroker().handleRemoteCancel(packet);
        } else if (dbg()) {
            plugin.getLogger().warning("[TRADE]   TradeBroker is null; cannot handle cancel.");
        }
    }
}
