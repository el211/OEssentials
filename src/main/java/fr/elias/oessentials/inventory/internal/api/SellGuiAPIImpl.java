package fr.elias.oessentials.inventory.internal.api;

import fr.elias.oessentials.api.ISellGuiAPI;
import fr.elias.oessentials.inventory.internal.sellgui.manager.SellGuiManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SellGuiAPIImpl implements ISellGuiAPI {
    private final SellGuiManager mgr;
    public SellGuiAPIImpl(SellGuiManager mgr) { this.mgr = mgr; }

    @Override public void openSell(@NotNull Player p) { mgr.openSell(p); }
    @Override public void reload() { mgr.reload(); }
}
