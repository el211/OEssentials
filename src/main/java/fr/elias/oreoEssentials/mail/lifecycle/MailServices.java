package fr.elias.oreoEssentials.mail.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface MailServices {
    fr.elias.oreoEssentials.mail.internal.MailService getMailService();
    fr.elias.oreoEssentials.mail.internal.MailListener getMailListener();
}
