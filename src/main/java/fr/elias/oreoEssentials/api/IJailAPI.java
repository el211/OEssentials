package fr.elias.oreoEssentials.api;

import fr.elias.oreoEssentials.jails.internal.jail.JailModels;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

/**
 * API for the Jail module.
 * Obtain via {@link OreoEssentialsAPI#jail()}. Returns {@code null} if disabled.
 */
public interface IJailAPI {

    /** Returns {@code true} if the given player is currently jailed. */
    boolean isJailed(@NotNull UUID playerId);

    /**
     * Returns the current sentence for the given player, or {@code null} if not jailed.
     *
     * @param playerId the player's UUID
     */
    @Nullable JailModels.Sentence getSentence(@NotNull UUID playerId);

    /**
     * Returns all configured jails, keyed by name.
     */
    @NotNull Map<String, JailModels.Jail> allJails();

    /**
     * Returns a specific jail by name, or {@code null} if not found.
     *
     * @param jailName the jail name
     */
    @Nullable JailModels.Jail getJail(@NotNull String jailName);

    /**
     * Releases the player from jail early.
     *
     * @param playerId the player's UUID
     * @return {@code true} if the player was jailed and is now released
     */
    boolean release(@NotNull UUID playerId);

    /**
     * Extends the player's sentence by the given number of milliseconds.
     *
     * @param playerId      the player's UUID
     * @param additionalMs  additional time in milliseconds
     * @param by            name of the staff member extending the sentence
     * @return {@code true} if the sentence was extended
     */
    boolean extendSentence(@NotNull UUID playerId, long additionalMs, @NotNull String by);

    /**
     * Returns {@code true} if the command is blocked for a jailed player.
     *
     * @param player  the jailed player
     * @param baseCmd the base command name (e.g. {@code "tp"})
     */
    boolean isCommandBlockedFor(@NotNull org.bukkit.entity.Player player, @NotNull String baseCmd);
}
