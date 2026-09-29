package fr.elias.oessentials.api;

/**
 * API for the CommandToggle module — enables/disables Bukkit commands at runtime.
 *
 * <p>Obtain via {@link OEssentialsAPI#commandToggle()}. Returns {@code null} if disabled.
 */
public interface ICommandToggleAPI {

    /**
     * Re-evaluates the toggle config and registers/unregisters commands accordingly.
     */
    void applyToggles();

    /**
     * Reloads the toggle configuration from disk and re-applies toggles.
     */
    void reload();
}
