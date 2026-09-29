package fr.elias.oessentials.api;

/**
 * API for the AutoReboot module — scheduled server restarts.
 *
 * <p>Obtain via {@link OEssentialsAPI#autoReboot()}. Returns {@code null} if disabled.
 */
public interface IAutoRebootAPI {

    /**
     * Stops the auto-reboot countdown and cancels any pending restart.
     */
    void stop();

    /**
     * Reloads configuration from disk and restarts the countdown.
     */
    void reload();
}
