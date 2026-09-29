package fr.elias.oreoEssentials.mail.internal.api;

import fr.elias.oreoEssentials.api.IMailAPI;
import fr.elias.oreoEssentials.mail.internal.MailService;
import fr.elias.oreoEssentials.mail.internal.model.MailMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public final class MailAPIImpl implements IMailAPI {

    private final MailService svc;

    public MailAPIImpl(MailService svc) { this.svc = svc; }

    @Override
    public @NotNull CompletableFuture<Void> sendMail(@NotNull UUID recipient, @NotNull String senderName,
                                                      UUID senderUuid, @NotNull String message) {
        return svc.sendTextMail(senderUuid, senderName, recipient, message);
    }

    @Override
    public @NotNull CompletableFuture<List<MailMessage>> getMail(@NotNull UUID playerId) {
        return svc.getMailbox(playerId);
    }

    @Override
    public @NotNull CompletableFuture<Long> unreadCount(@NotNull UUID playerId) {
        return svc.unreadCountAsync(playerId);
    }

    @Override
    public @NotNull CompletableFuture<Void> markAllRead(@NotNull UUID playerId) {
        return svc.markAllRead(playerId);
    }

    @Override
    public @NotNull CompletableFuture<Void> deleteMail(@NotNull UUID playerId, @NotNull String mailId) {
        return svc.deleteMail(playerId, mailId);
    }

    @Override
    public @NotNull CompletableFuture<Void> clearMail(@NotNull UUID playerId) {
        return svc.clearMail(playerId);
    }
}
