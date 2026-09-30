package fr.elias.oessentials.mail.internal.model;

import java.util.UUID;

/**
 * A single mail entry. Supports text messages, item attachments, or both.
 * Broadcast mails are stored with recipientUuid == null in the broadcast
 * table and are lazily copied per-player on first encounter.
 */
public final class MailMessage {

    private final String  id;
    private final UUID    recipientUuid;  // null → broadcast record (not a player mail)
    private final UUID    senderUuid;     // null → server / console
    private final String  senderName;
    private final String  message;        // null if item-only
    private final String  itemData;       // null if text-only; Base64 BukkitObjectOutputStream
    private final long    sentAt;
    private       boolean read;
    private       boolean itemClaimed;
    private final long    expiresAt;      // epoch ms; 0 = never expires

    public MailMessage(String id, UUID recipientUuid, UUID senderUuid,
                       String senderName, String message, String itemData,
                       long sentAt, boolean read, boolean itemClaimed, long expiresAt) {
        this.id            = id;
        this.recipientUuid = recipientUuid;
        this.senderUuid    = senderUuid;
        this.senderName    = senderName;
        this.message       = message;
        this.itemData      = itemData;
        this.sentAt        = sentAt;
        this.read          = read;
        this.itemClaimed   = itemClaimed;
        this.expiresAt     = expiresAt;
    }

    public String  getId()            { return id; }
    public UUID    getRecipientUuid() { return recipientUuid; }
    public UUID    getSenderUuid()    { return senderUuid; }
    public String  getSenderName()    { return senderName; }
    public String  getMessage()       { return message; }
    public String  getItemData()      { return itemData; }
    public long    getSentAt()        { return sentAt; }
    public boolean isRead()           { return read; }
    public boolean isItemClaimed()    { return itemClaimed; }
    public long    getExpiresAt()     { return expiresAt; }

    public boolean hasItem()    { return itemData != null && !itemData.isBlank(); }
    public boolean hasMessage() { return message  != null && !message.isBlank(); }
    public boolean isExpired()  { return expiresAt > 0 && System.currentTimeMillis() > expiresAt; }

    public void setRead(boolean v)        { this.read = v; }
    public void setItemClaimed(boolean v) { this.itemClaimed = v; }
}
