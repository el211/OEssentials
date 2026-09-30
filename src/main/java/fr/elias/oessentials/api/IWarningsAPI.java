package fr.elias.oessentials.api;

import fr.elias.oessentials.warnings.internal.WarnService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * API for the Warnings module — player warning system.
 *
 * <p>Obtain via {@link OEssentialsAPI#warnings()}. Returns {@code null} if disabled.
 */
public interface IWarningsAPI {

    /**
     * Issues a warning to a player.
     *
     * @param target      UUID of the player being warned
     * @param issuer      UUID of the staff member issuing the warning
     * @param issuerName  display name of the issuer
     * @param reason      reason for the warning
     * @return the new active warning count for the player
     */
    int warn(@NotNull UUID target, @NotNull UUID issuer, @NotNull String issuerName, @NotNull String reason);

    /**
     * Removes a specific warning by its ID.
     *
     * @param target the warned player's UUID
     * @param warnId the warning ID
     * @return {@code true} if the warning was found and removed
     */
    boolean removeById(@NotNull UUID target, @NotNull String warnId);

    /**
     * Clears all warnings for the player.
     *
     * @param target the player's UUID
     */
    void clearAll(@NotNull UUID target);

    /**
     * Returns all active (non-expired) warnings for the player.
     *
     * @param target the player's UUID
     */
    @NotNull List<WarnService.WarnEntry> getActive(@NotNull UUID target);

    /**
     * Returns the count of active warnings for the player.
     *
     * @param target the player's UUID
     */
    int getActiveCount(@NotNull UUID target);

    /**
     * Returns the maximum number of warnings before an action is triggered.
     */
    int getMaxWarnings();
}
