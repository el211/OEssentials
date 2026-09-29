package fr.elias.oessentials.api;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * API for the JumpPads module.
 * Obtain via {@link OEssentialsAPI#jumpPads()}. Returns {@code null} if disabled.
 */
public interface IJumpPadsAPI {

    /** Returns the names of all configured jump pads. */
    @NotNull Set<String> listNames();

    /**
     * Creates or overwrites a jump pad at the given block location.
     *
     * @param name        the jump pad identifier
     * @param blockLoc    the block location of the pressure plate / block
     * @param power       horizontal launch power
     * @param upward      vertical launch power
     * @param useLookDir  {@code true} to launch in the direction the player is looking
     * @return {@code true} if created successfully
     */
    boolean create(@NotNull String name,
                   @NotNull Location blockLoc,
                   double power,
                   double upward,
                   boolean useLookDir);

    /**
     * Removes the jump pad with the given name.
     *
     * @param name the jump pad identifier
     * @return {@code true} if it existed and was removed
     */
    boolean remove(@NotNull String name);
}
