package fr.elias.oessentials.messaging.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.messaging.internal.rabbitmq.packet.PacketManager;

/** Published on the server scheduler after messaging subscriptions have been initialized. */
@ModuleApi("events")
public record MessagingReady(PacketManager packets) {}
