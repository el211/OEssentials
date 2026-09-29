package fr.elias.oessentials.api;

import fr.elias.oessentials.commandcontrol.internal.aliases.AliasService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * API for the Aliases module — custom command aliases with checks and cooldowns.
 *
 * <p>Obtain via {@link OEssentialsAPI#aliases()}. Returns {@code null} if disabled.
 */
public interface IAliasesAPI {

    /**
     * Returns all alias definitions (unmodifiable view).
     */
    @NotNull Map<String, AliasService.AliasDef> all();

    /**
     * Returns {@code true} if an alias with the given name exists.
     *
     * @param name alias name (case-insensitive)
     */
    boolean exists(@NotNull String name);

    /**
     * Returns the alias definition for the given name, or {@code null} if not found.
     *
     * @param name alias name (case-insensitive)
     */
    @Nullable AliasService.AliasDef get(@NotNull String name);

    /**
     * Adds or replaces an alias definition.
     *
     * @param def the alias definition to store
     */
    void put(@NotNull AliasService.AliasDef def);

    /**
     * Removes the alias with the given name.
     *
     * @param name alias name (case-insensitive)
     */
    void remove(@NotNull String name);

    /**
     * Reloads alias definitions from disk (discards unsaved in-memory changes).
     */
    void load();

    /**
     * Saves the current in-memory alias definitions to disk and re-registers commands.
     */
    void save();
}
