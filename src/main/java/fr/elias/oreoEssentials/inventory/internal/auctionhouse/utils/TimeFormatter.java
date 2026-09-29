package fr.elias.oreoEssentials.inventory.internal.auctionhouse.utils;

import java.util.concurrent.TimeUnit;

public final class TimeFormatter {

    private TimeFormatter() {}

    public static String format(long milliseconds) {
        if (milliseconds <= 0) return "Expired";

        long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
        milliseconds -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        milliseconds -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);

        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("d ");
        if (hours > 0) sb.append(hours).append("h ");
        if (minutes > 0) sb.append(minutes).append("m ");
        if (seconds > 0 && days == 0) sb.append(seconds).append("s");

        String result = sb.toString().trim();
        return result.isEmpty() ? "Expired" : result;
    }

    public static String formatShort(long milliseconds) {
        if (milliseconds <= 0) return "Expired";
        long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
        if (days > 0) return days + "d";
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        if (hours > 0) return hours + "h";
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        if (minutes > 0) return minutes + "m";
        return TimeUnit.MILLISECONDS.toSeconds(milliseconds) + "s";
    }
}