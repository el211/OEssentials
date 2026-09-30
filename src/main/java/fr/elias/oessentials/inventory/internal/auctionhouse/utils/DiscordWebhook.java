package fr.elias.oessentials.inventory.internal.auctionhouse.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class DiscordWebhook {

    private final String webhookUrl;
    private String botName;
    private String botAvatarUrl;
    private String content;
    private String title;
    private String description;
    private int colorRgb = -1;
    private String thumbnailUrl;
    private boolean showTimestamp = true;

    public DiscordWebhook(String webhookUrl) { this.webhookUrl = webhookUrl; }

    public DiscordWebhook setBotName(String n)         { this.botName = n; return this; }
    public DiscordWebhook setBotAvatarUrl(String u)    { this.botAvatarUrl = u; return this; }
    public DiscordWebhook setContent(String c)         { this.content = c; return this; }
    public DiscordWebhook setTitle(String t)           { this.title = t; return this; }
    public DiscordWebhook setDescription(String d)     { this.description = d; return this; }
    public DiscordWebhook setColor(int rgb)            { this.colorRgb = rgb & 0xFFFFFF; return this; }
    public DiscordWebhook setThumbnailUrl(String u)    { this.thumbnailUrl = u; return this; }
    public DiscordWebhook setShowTimestamp(boolean b)   { this.showTimestamp = b; return this; }

    public void execute() {
        try {
            URL url = new URL(webhookUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "OEssentials-AuctionHouse");
            conn.setDoOutput(true);

            String json = buildJson();
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();
            if (code < 200 || code >= 300) {
                throw new RuntimeException("Discord webhook failed: HTTP " + code);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildJson() {
        StringBuilder j = new StringBuilder("{");
        if (botName != null)      j.append("\"username\":\"").append(esc(botName)).append("\",");
        if (botAvatarUrl != null) j.append("\"avatar_url\":\"").append(esc(botAvatarUrl)).append("\",");
        if (content != null)      j.append("\"content\":\"").append(esc(content)).append("\",");

        j.append("\"embeds\":[{");
        if (title != null)        j.append("\"title\":\"").append(esc(title)).append("\",");
        if (description != null)  j.append("\"description\":\"").append(esc(description)).append("\",");
        if (colorRgb >= 0)        j.append("\"color\":").append(colorRgb).append(",");
        if (thumbnailUrl != null) j.append("\"thumbnail\":{\"url\":\"").append(esc(thumbnailUrl)).append("\"},");
        if (showTimestamp)        j.append("\"timestamp\":\"").append(Instant.now()).append("\",");

        if (j.charAt(j.length() - 1) == ',') j.deleteCharAt(j.length() - 1);
        j.append("}]");
        if (j.charAt(j.length() - 1) == ',') j.deleteCharAt(j.length() - 1);
        j.append("}");
        return j.toString();
    }

    private static String esc(String t) {
        return t.replace("\\","\\\\").replace("\"","\\\"")
                .replace("\n","\\n").replace("\r","\\r").replace("\t","\\t");
    }
}