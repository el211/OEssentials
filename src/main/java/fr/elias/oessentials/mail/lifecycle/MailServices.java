package fr.elias.oessentials.mail.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface MailServices {
    fr.elias.oessentials.mail.internal.MailService getMailService();
    fr.elias.oessentials.mail.internal.MailListener getMailListener();
}
