package fr.elias.oreoEssentials.api;

import fr.elias.oreoEssentials.mail.internal.model.MailMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * API for the Mail module — in-game mail between players.
 *
 * <p>Obtain via {@link OreoEssentialsAPI#mail()}. Returns {@code null} if disabled.
 */
public interface IMailAPI {

    /**
     * Sends a text mail to the recipient (works for offline players).
     */
    @NotNull CompletableFuture<Void> sendMail(@NotNull UUID recipient, @NotNull String senderName,
                                               UUID senderUuid, @NotNull String message);

    /**
     * Returns a future that resolves to this player's mailbox, newest first.
     */
    @NotNull CompletableFuture<List<MailMessage>> getMail(@NotNull UUID playerId);

    /**
     * Returns a future that resolves to the number of unread messages.
     */
    @NotNull CompletableFuture<Long> unreadCount(@NotNull UUID playerId);

    /**
     * Marks all messages for the player as read.
     */
    @NotNull CompletableFuture<Void> markAllRead(@NotNull UUID playerId);

    /**
     * Deletes a specific mail by its ID.
     */
    @NotNull CompletableFuture<Void> deleteMail(@NotNull UUID playerId, @NotNull String mailId);

    /**
     * Clears all mail for the player.
     */
    @NotNull CompletableFuture<Void> clearMail(@NotNull UUID playerId);
}
