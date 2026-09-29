package fr.elias.oreoEssentials.inventory.internal.auctionhouse.hooks;

import fr.elias.oreoEssentials.inventory.internal.auctionhouse.AuctionHouseModule;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AuctionPlaceholders extends PlaceholderExpansion {

    private final AuctionHouseModule module;

    public AuctionPlaceholders(AuctionHouseModule module) { this.module = module; }

    @Override public @NotNull String getIdentifier() { return "oreoah"; }
    @Override public @NotNull String getAuthor()     { return "OreoEssentials"; }
    @Override public @NotNull String getVersion()    { return "1.0.0"; }
    @Override public boolean persist()               { return true; }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String id) {
        if (player == null || !module.enabled()) return "";
        return switch (id.toLowerCase()) {
            case "active_listings"  -> String.valueOf(module.getAllActiveAuctions().size());
            case "player_listings"  -> String.valueOf(module.getPlayerActiveListings(player.getUniqueId()).size());
            case "player_sales"     -> String.valueOf(module.getPlayerSold(player.getUniqueId()).size());
            case "player_expired"   -> String.valueOf(module.getPlayerExpired(player.getUniqueId()).size());
            default -> null;
        };
    }
}