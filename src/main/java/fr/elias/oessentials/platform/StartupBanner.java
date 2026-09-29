package fr.elias.oessentials.platform;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Prints the OEssentials startup banner: a big ASCII logo with the live plugin
 * version, a loading subtitle, a checklist of every loaded module, and a checklist
 * of every optional integration the plugin can hook into.
 */
public final class StartupBanner {

    private StartupBanner() {}

    /** Minimal 5-row block font for the letters used in "OEssentials". */
    private static final Map<Character, String[]> FONT = new LinkedHashMap<>();
    static {
        FONT.put('O', new String[]{" ### ", "#   #", "#   #", "#   #", " ### "});
        FONT.put('E', new String[]{"#####", "#    ", "#### ", "#    ", "#####"});
        FONT.put('S', new String[]{" ####", "#    ", " ### ", "    #", "#### "});
        FONT.put('N', new String[]{"#   #", "##  #", "# # #", "#  ##", "#   #"});
        FONT.put('T', new String[]{"#####", "  #  ", "  #  ", "  #  ", "  #  "});
        FONT.put('I', new String[]{"###", " # ", " # ", " # ", "###"});
        FONT.put('A', new String[]{" ### ", "#   #", "#####", "#   #", "#   #"});
        FONT.put('L', new String[]{"#    ", "#    ", "#    ", "#    ", "#####"});
        FONT.put(' ', new String[]{"  ", "  ", "  ", "  ", "  "});
    }

    /** Optional plugins OEssentials integrates with: display name -> Bukkit plugin name. */
    private static final Map<String, String> INTEGRATIONS = new LinkedHashMap<>();
    static {
        INTEGRATIONS.put("Vault", "Vault");
        INTEGRATIONS.put("PlaceholderAPI", "PlaceholderAPI");
        INTEGRATIONS.put("PacketEvents", "packetevents");
        INTEGRATIONS.put("WorldGuard", "WorldGuard");
        INTEGRATIONS.put("LuckPerms", "LuckPerms");
        INTEGRATIONS.put("FastAsyncWorldEdit", "FastAsyncWorldEdit");
        INTEGRATIONS.put("MythicMobs", "MythicMobs");
        INTEGRATIONS.put("ItemsAdder", "ItemsAdder");
        INTEGRATIONS.put("Nexo", "Nexo");
        INTEGRATIONS.put("FancyNpcs", "FancyNpcs");
        INTEGRATIONS.put("Floodgate", "floodgate");
    }

    private static List<String> renderLogo(String text) {
        String[] rows = {"", "", "", "", ""};
        for (char c : text.toUpperCase(Locale.ROOT).toCharArray()) {
            String[] glyph = FONT.getOrDefault(c, FONT.get(' '));
            for (int i = 0; i < rows.length; i++) rows[i] += glyph[i] + " ";
        }
        return List.of(rows);
    }

    public static void print(JavaPlugin plugin, List<String> moduleIds) {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        String version = plugin.getDescription().getVersion();

        send(console, "");
        for (String line : renderLogo("OEssentials")) send(console, ChatColor.AQUA + line);
        send(console, ChatColor.DARK_AQUA + "  v" + version
                + ChatColor.DARK_GRAY + "  -  " + ChatColor.GRAY + "loading your core");
        send(console, "");

        // ---- Modules checklist -------------------------------------------------
        send(console, ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + "Modules "
                + ChatColor.GRAY + "(" + moduleIds.size() + " loaded)");
        List<String> sorted = new ArrayList<>(moduleIds);
        sorted.sort(String::compareTo);
        int columns = 3, cellWidth = 22;
        for (int i = 0; i < sorted.size(); i += columns) {
            StringBuilder row = new StringBuilder("   ");
            for (int j = i; j < Math.min(i + columns, sorted.size()); j++) {
                row.append(ChatColor.GREEN).append("[+] ")
                   .append(ChatColor.GRAY).append(pad(sorted.get(j), cellWidth));
            }
            send(console, row.toString());
        }
        send(console, "");

        // ---- Integrations checklist -------------------------------------------
        send(console, ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + "Integrations "
                + ChatColor.GRAY + "(optional)");
        for (Map.Entry<String, String> e : INTEGRATIONS.entrySet()) {
            boolean present = Bukkit.getPluginManager().getPlugin(e.getValue()) != null;
            String mark = present ? ChatColor.GREEN + "[+]" : ChatColor.DARK_GRAY + "[-]";
            String name = (present ? ChatColor.WHITE : ChatColor.DARK_GRAY) + e.getKey();
            String note = present ? "" : ChatColor.DARK_GRAY + " (not installed)";
            send(console, "   " + mark + " " + name + note);
        }
        send(console, "");
        send(console, ChatColor.AQUA + "OEssentials " + ChatColor.DARK_GRAY + "v" + version
                + ChatColor.GREEN + "  ready." + ChatColor.RESET);
        send(console, "");
    }

    private static String pad(String s, int width) {
        if (s.length() >= width) return s.substring(0, width - 1) + " ";
        StringBuilder b = new StringBuilder(s);
        while (b.length() < width) b.append(' ');
        return b.toString();
    }

    private static void send(ConsoleCommandSender console, String message) {
        console.sendMessage(message);
    }
}
