package fr.elias.oreoEssentials.messaging.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.messaging.internal.rabbitmq.packet.PacketManager;

/** Published on the server scheduler after messaging subscriptions have been initialized. */
@ModuleApi("events")
public record MessagingReady(PacketManager packets) {}
