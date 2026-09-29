package fr.elias.oreoEssentials.inventory.internal.auctionhouse.storage;

import fr.elias.oreoEssentials.inventory.internal.auctionhouse.models.Auction;

import java.util.List;


public interface AuctionStorage {

    AuctionSnapshot loadAll();

    void saveAll(AuctionSnapshot snapshot);

    default void flush() {}

    record AuctionSnapshot(
            List<Auction> active,
            List<Auction> expired,
            List<Auction> sold
    ) {}
}