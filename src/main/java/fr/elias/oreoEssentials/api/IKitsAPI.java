package fr.elias.oreoEssentials.api;

import fr.elias.oreoEssentials.kits.internal.Kit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * API for the Kits module.
 * Obtain via {@link OreoEssentialsAPI#kits()}. Returns {@code null} if disabled.
 */
public interface IKitsAPI {

    /** Returns {@code true} if the kits module is enabled. */
    boolean isEnabled();

    /**
     * Returns an unmodifiable map of all kits, keyed by kit ID (lowercase).
     */
    @NotNull Map<String, Kit> getKits();

    /**
     * Returns the kit with the given ID, or {@code null} if it doesn't exist.
     *
     * @param kitId the kit identifier (case-insensitive)
     */
    @Nullable Kit getKit(@NotNull String kitId);

    /**
     * Returns the number of seconds remaining before the player can claim the kit again.
     * Returns {@code 0} if the kit is ready to claim.
     *
     * @param player the player
     * @param kit    the kit to check
     */
    long getSecondsLeft(@NotNull Player player, @NotNull Kit kit);

    /**
     * Claims a kit for the player, giving items and running reward commands.
     * Respects cooldowns.
     *
     * @param player the player claiming
     * @param kitId  the kit ID to claim
     * @return {@code true} if successfully claimed
     */
    boolean claim(@NotNull Player player, @NotNull String kitId);
}
