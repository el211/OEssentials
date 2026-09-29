package fr.elias.oessentials.mail.internal.repository;

import fr.elias.oessentials.mail.internal.model.MailMessage;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MailRepository {

    // ── Individual player mail ────────────────────────────────────────────────

    CompletableFuture<Void>              save(UUID recipientUuid, MailMessage msg);
    CompletableFuture<List<MailMessage>> loadForPlayer(UUID recipientUuid);
    /** Persist in-place mutations (read flag, itemClaimed flag). */
    CompletableFuture<Void>              update(UUID recipientUuid, MailMessage msg);
    CompletableFuture<Void>              delete(UUID recipientUuid, String mailId);
    CompletableFuture<Void>              clearAll(UUID recipientUuid);

    // ── Global broadcasts ─────────────────────────────────────────────────────

    CompletableFuture<Void>              saveBroadcast(MailMessage broadcast);
    CompletableFuture<List<MailMessage>> loadBroadcasts();

    /** True if the player has already received (been given a copy of) this broadcast. */
    CompletableFuture<Boolean>           hasReceivedBroadcast(UUID playerUuid, String broadcastId);
    /** Record that the player has received this broadcast so we don't duplicate it. */
    CompletableFuture<Void>              markBroadcastReceived(UUID playerUuid, String broadcastId);
}
