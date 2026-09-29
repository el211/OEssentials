package fr.elias.oreoEssentials.api;

import fr.elias.oreoEssentials.portals.internal.PortalsManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * API for the Portals module.
 * Obtain via {@link OreoEssentialsAPI#portals()}. Returns {@code null} if disabled.
 */
public interface IPortalsAPI {

    /** Returns {@code true} if the portals module is enabled. */
    boolean isEnabled();

    /** Returns the names of all configured portals. */
    @NotNull Set<String> listNames();

    /**
     * Returns a portal by name, or {@code null} if not found.
     *
     * @param name the portal name
     */
    @Nullable PortalsManager.Portal get(@NotNull String name);

    /**
     * Deletes the portal with the given name.
     *
     * @param name the portal name
     * @return {@code true} if it existed and was removed
     */
    boolean remove(@NotNull String name);

    /**
     * Updates the destination location for a portal.
     *
     * @param name     the portal name
     * @param location the new destination
     */
    void updateDestination(@NotNull String name, @NotNull Location location);

    /**
     * Updates the destination server for a cross-server portal.
     *
     * @param name   the portal name
     * @param server the target server name
     */
    void updateDestServer(@NotNull String name, @NotNull String server);

    /**
     * Updates the permission required to use a portal.
     *
     * @param name       the portal name
     * @param permission the required permission node, or empty to remove
     */
    void updatePermission(@NotNull String name, @NotNull String permission);
}
