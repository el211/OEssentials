package fr.elias.oessentials.api;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * API for cross-server moderation actions (ModBridge).
 * Obtain via {@link OEssentialsAPI#crossServer()}. Returns {@code null} if disabled.
 *
 * <p>All methods send a packet to the target player's server via RabbitMQ and execute
 * the action remotely. The target does not need to be on this server.
 */
public interface ICrossServerAPI {

    /** Kills the target player on their server. */
    void kill(@NotNull UUID targetId, @NotNull String targetName);

    /** Kicks the target player from their server with the given reason. */
    void kick(@NotNull UUID targetId, @NotNull String targetName, @NotNull String reason);

    /** Bans the target player across all servers. */
    void ban(@NotNull UUID targetId, @NotNull String targetName, @NotNull String reason);

    /**
     * Toggles freeze on the target player for the given duration.
     *
     * @param seconds duration in seconds (0 = toggle off immediately)
     */
    void freezeToggle(@NotNull UUID targetId, @NotNull String targetName, long seconds);

    /** Toggles vanish for the target player on their server. */
    void vanishToggle(@NotNull UUID targetId, @NotNull String targetName);

    /** Cycles through game modes for the target player. */
    void gamemodeCycle(@NotNull UUID targetId, @NotNull String targetName);

    /** Heals the target player to full health. */
    void heal(@NotNull UUID targetId, @NotNull String targetName);

    /** Restores the target player's hunger to full. */
    void feed(@NotNull UUID targetId, @NotNull String targetName);
}
