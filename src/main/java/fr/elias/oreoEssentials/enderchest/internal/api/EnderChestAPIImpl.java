package fr.elias.oreoEssentials.enderchest.internal.api;

import fr.elias.oreoEssentials.api.IEnderChestAPI;
import fr.elias.oreoEssentials.enderchest.internal.EnderChestService;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EnderChestAPIImpl implements IEnderChestAPI {
    private final EnderChestService svc;
    public EnderChestAPIImpl(EnderChestService svc) { this.svc = svc; }

    @Override public void open(@NotNull Player p) { svc.open(p); }
    @Override public @NotNull ItemStack[] loadFor(@NotNull UUID id, int rows) { return svc.loadFor(id, rows); }
    @Override public void saveFor(@NotNull UUID id, int rows, @NotNull ItemStack[] contents) { svc.saveFor(id, rows, contents); }
    @Override public int resolveSlots(@NotNull Player p) { return svc.resolveSlots(p); }
}
