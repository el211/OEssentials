package fr.elias.oessentials.grouprtp.internal.command;

import fr.elias.oessentials.platform.commands.OreoCommand;
import fr.elias.oessentials.grouprtp.internal.GroupRtpConfig;
import fr.elias.oessentials.grouprtp.internal.GroupRtpModule;
import fr.elias.oessentials.grouprtp.internal.model.GroupRtpPortalDef;
import fr.elias.oessentials.grouprtp.internal.model.GroupSession;
import fr.elias.oessentials.grouprtp.internal.service.GroupRtpService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

/**
 * /grouprtp admin command.
 *
 * <pre>
 *   /grouprtp wand              — get the selection wand
 *   /grouprtp pos1              — set pos1 from current location
 *   /grouprtp pos2              — set pos2 from current location
 *   /grouprtp create &lt;id&gt; [world] — create portal from pos1/pos2
 *   /grouprtp delete &lt;id&gt;      — delete portal
 *   /grouprtp list              — list portals
 *   /grouprtp info &lt;id&gt;        — show portal info
 *   /grouprtp sessions          — show active sessions
 *   /grouprtp clearcooldown &lt;player&gt; — clear a player's cooldown
 *   /grouprtp reload            — reload config
 * </pre>
 */
public final class GroupRtpCommand implements OreoCommand, TabCompleter {

    private static final String WAND_NAME = ChatColor.GOLD + "GroupRTP Wand";
    private static final List<String> SUBS = List.of(
            "wand", "pos1", "pos2", "create", "delete", "list",
            "info", "sessions", "clearcooldown", "reload");

    private final GroupRtpModule  module;
    private final GroupRtpConfig  config;
    private final GroupRtpService service;

    /** Per-admin pending selections (not persistent). */
    private final Map<UUID, Vector> pos1 = new HashMap<>();
    private final Map<UUID, Vector> pos2 = new HashMap<>();

    public GroupRtpCommand(GroupRtpModule module) {
        this.module  = module;
        this.config  = module.getConfig();
        this.service = module.getService();
    }

    @Override public String       name()       { return "grouprtp"; }
    @Override public List<String> aliases()    { return List.of("grtp"); }
    @Override public String       permission() { return "oreo.grouprtp.admin"; }
    @Override public String       usage()      { return "wand|pos1|pos2|create|delete|list|info|sessions|reload"; }
    @Override public boolean      playerOnly() { return false; }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(permission())) {
            sender.sendMessage(c("&cNo permission."));
            return true;
        }
        if (args.length == 0) { sendHelp(sender, label); return true; }

        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "wand"          -> cmdWand(sender);
            case "pos1"          -> cmdPos(sender, 1);
            case "pos2"          -> cmdPos(sender, 2);
            case "create"        -> cmdCreate(sender, args);
            case "delete"        -> cmdDelete(sender, args);
            case "list"          -> cmdList(sender);
            case "info"          -> cmdInfo(sender, args);
            case "sessions"      -> cmdSessions(sender);
            case "clearcooldown" -> cmdClearCooldown(sender, args);
            case "reload"        -> cmdReload(sender);
            default              -> sendHelp(sender, label);
        }
        return true;
    }

    // ── Subcommands ───────────────────────────────────────────────────────────

    private void cmdWand(CommandSender sender) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(c("&cPlayer only.")); return;
        }
        ItemStack wand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta  meta = wand.getItemMeta();
        meta.setDisplayName(WAND_NAME);
        meta.setLore(List.of(c("&7Left-click: &ePos 1"), c("&7Right-click: &ePos 2")));
        wand.setItemMeta(meta);
        p.getInventory().addItem(wand);
        p.sendMessage(c("&aGroupRTP Wand given. Left-click = Pos1, Right-click = Pos2."));
    }

    private void cmdPos(CommandSender sender, int num) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(c("&cPlayer only.")); return;
        }
        Vector v = p.getLocation().toVector().toBlockVector();
        if (num == 1) { pos1.put(p.getUniqueId(), v); p.sendMessage(c("&aPos1 set to " + vecStr(v))); }
        else          { pos2.put(p.getUniqueId(), v); p.sendMessage(c("&aPos2 set to " + vecStr(v))); }
    }

    private void cmdCreate(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(c("&eUsage: /grouprtp create <id> [rtp-world]")); return;
        }
        String id = args[1].toLowerCase(Locale.ROOT);
        if (config.get(id).isPresent()) {
            sender.sendMessage(c("&cPortal '&e" + id + "&c' already exists. Delete it first.")); return;
        }
        if (!(sender instanceof Player p)) {
            sender.sendMessage(c("&cPlayer only for region selection.")); return;
        }
        Vector v1 = pos1.get(p.getUniqueId());
        Vector v2 = pos2.get(p.getUniqueId());
        if (v1 == null || v2 == null) {
            sender.sendMessage(c("&cSet pos1 and pos2 first (use /grouprtp wand).")); return;
        }

        String rtpWorld = args.length > 2 ? args[2] : p.getWorld().getName();
        BoundingBox box = BoundingBox.of(v1, v2);

        // Build a default definition; the server owner can edit group-rtp.yml for fine-tuning
        GroupRtpPortalDef def = new GroupRtpPortalDef(
                id, "&6&l" + id, p.getWorld().getName(), box,
                rtpWorld, "",
                3000, 100, 0, 0,
                50, 250, 50,
                Set.of("LAVA","FIRE","CACTUS","MAGMA_BLOCK","POWDER_SNOW"), Set.of(),
                2, 10, 10, 5.0,
                300_000L, "",
                new LinkedHashMap<>(),
                "PORTAL", 3, "END_ROD", 30,
                "BLOCK_PORTAL_AMBIENT", "ENTITY_ENDERMAN_TELEPORT");

        config.addPortalToFile(id, def);
        sender.sendMessage(c("&aPortal '&e" + id + "&a' created! Edit &7group-rtp.yml&a for fine-tuning then &e/grouprtp reload&a."));
    }

    private void cmdDelete(CommandSender sender, String[] args) {
        if (args.length < 2) { sender.sendMessage(c("&eUsage: /grouprtp delete <id>")); return; }
        String id = args[1].toLowerCase(Locale.ROOT);
        if (config.get(id).isEmpty()) {
            sender.sendMessage(c("&cPortal '&e" + id + "&c' not found.")); return;
        }
        config.removePortalFromFile(id);
        sender.sendMessage(c("&aPortal '&e" + id + "&a' deleted."));
    }

    private void cmdList(CommandSender sender) {
        Map<String, GroupRtpPortalDef> portals = config.getPortals();
        if (portals.isEmpty()) {
            sender.sendMessage(c("&7No Group RTP portals configured.")); return;
        }
        sender.sendMessage(c("&6&lGroup RTP Portals (" + portals.size() + ")"));
        portals.forEach((id, def) -> {
            String active = service.getSessions().containsKey(id) ? " &a[ACTIVE]" : "";
            sender.sendMessage(c("  &e" + id + " &7→ &f" + def.getDisplayName() + active));
        });
    }

    private void cmdInfo(CommandSender sender, String[] args) {
        if (args.length < 2) { sender.sendMessage(c("&eUsage: /grouprtp info <id>")); return; }
        String id = args[1].toLowerCase(Locale.ROOT);
        Optional<GroupRtpPortalDef> opt = config.get(id);
        if (opt.isEmpty()) {
            sender.sendMessage(c("&cPortal '&e" + id + "&c' not found.")); return;
        }
        GroupRtpPortalDef def = opt.get();
        sender.sendMessage(c("&6--- &lGroup RTP: &e" + id + " &6---"));
        sender.sendMessage(c("  &7World: &f" + def.getWorldName() + " &7→ RTP: &f" + def.getRtpWorld()
                + (def.getRtpServer().isEmpty() ? "" : " (" + def.getRtpServer() + ")")));
        sender.sendMessage(c("  &7Radius: &f" + (int)def.getRtpMinRadius() + " - " + (int)def.getRtpRadius()
                + " &7| Center: &f" + (int)def.getRtpCenterX() + ", " + (int)def.getRtpCenterZ()));
        sender.sendMessage(c("  &7Players: &f" + def.getMinPlayers() + " - " + def.getMaxPlayers()
                + " &7| Countdown: &f" + def.getCountdownSeconds() + "s"));
        sender.sendMessage(c("  &7Cooldown: &f" + (def.getCooldownMs()/1000) + "s"
                + " &7| Cluster radius: &f" + def.getClusterRadius()));
        GroupSession session = service.getSessions().get(id);
        if (session != null) {
            sender.sendMessage(c("  &aActive session: &f" + session.size() + " players | "
                    + session.getState() + (session.isCountdown() ? " (" + session.getSecondsLeft() + "s)" : "")));
        }
    }

    private void cmdSessions(CommandSender sender) {
        Map<String, GroupSession> sessions = service.getSessions();
        if (sessions.isEmpty()) {
            sender.sendMessage(c("&7No active sessions.")); return;
        }
        sender.sendMessage(c("&6Active sessions:"));
        sessions.forEach((id, s) ->
                sender.sendMessage(c("  &e" + id + " &7| &f" + s.size() + " players | "
                        + s.getState()
                        + (s.isCountdown() ? " (&e" + s.getSecondsLeft() + "s&7 left)" : ""))));
    }

    private void cmdClearCooldown(CommandSender sender, String[] args) {
        if (args.length < 2) { sender.sendMessage(c("&eUsage: /grouprtp clearcooldown <player>")); return; }
        org.bukkit.OfflinePlayer target = org.bukkit.Bukkit.getOfflinePlayer(args[1]);
        service.clearCooldown(target.getUniqueId());
        sender.sendMessage(c("&aCooldown cleared for &e" + target.getName() + "&a."));
    }

    private void cmdReload(CommandSender sender) {
        service.reload();
        sender.sendMessage(c("&a[GroupRTP] Reloaded " + config.getPortals().size() + " portal(s)."));
    }

    // ── Wand hook (called from GroupRtpWandListener) ──────────────────────────

    public void setPos(UUID player, int num, org.bukkit.Location loc) {
        Vector v = loc.toVector().toBlockVector();
        if (num == 1) pos1.put(player, v);
        else          pos2.put(player, v);
    }

    public static boolean isWand(ItemStack item) {
        return item != null && item.getType() == Material.BLAZE_ROD
                && item.hasItemMeta()
                && WAND_NAME.equals(item.getItemMeta().getDisplayName());
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static void sendHelp(CommandSender sender, String label) {
        sender.sendMessage(c("&6/&e" + label + " &7wand &8| &7pos1 &8| &7pos2 &8| &7create <id> [world]"));
        sender.sendMessage(c("&6/&e" + label + " &7delete <id> &8| &7list &8| &7info <id> &8| &7sessions"));
        sender.sendMessage(c("&6/&e" + label + " &7clearcooldown <player> &8| &7reload"));
    }

    private static String c(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    private static String vecStr(Vector v) {
        return (int)v.getX() + ", " + (int)v.getY() + ", " + (int)v.getZ();
    }

    // ── Tab completion ────────────────────────────────────────────────────────

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission(permission())) return Collections.emptyList();
        if (args.length == 1) {
            return SUBS.stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }
        if (args.length == 2) {
            String sub = args[0].toLowerCase();
            if (sub.equals("delete") || sub.equals("info")) {
                return config.getPortals().keySet().stream()
                        .filter(id -> id.startsWith(args[1].toLowerCase())).collect(Collectors.toList());
            }
            if (sub.equals("clearcooldown")) {
                return org.bukkit.Bukkit.getOnlinePlayers().stream()
                        .map(Player::getName)
                        .filter(n -> n.toLowerCase().startsWith(args[1].toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
