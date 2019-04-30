package club.imaginears.core.utils;

import club.imaginears.core.Core;
import club.imaginears.core.objects.Rank;
import club.imaginears.core.objects.User;
import com.google.common.collect.Lists;
import me.lucko.luckperms.LuckPerms;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class GUIs implements Listener {

    public static String currentGUI = null;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle() == null) {
            return;
        }
        if (e.getView().getTitle().contains("Edit User")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            OfflinePlayer target = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getLocalizedName());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("profile")) {
                openProfile(p, target);
            }
            if (itemname.contains("rank")) {
                openRankGUI(p, target);
            }
            if (itemname.contains("backpack")) {
                Chat.sendMessage(p, "Debug", "open backpack");
                p.closeInventory();
            }
            if (itemname.contains("rides")) {
                Chat.sendMessage(p, "Debug", "open rides");
                p.closeInventory();
            }
            if (itemname.contains("friends")) {
                Chat.sendMessage(p, "Debug", "open friends");
                p.closeInventory();
            }
            if (itemname.contains("punishments")) {
                Chat.sendMessage(p, "Debug", "open punishments");
                p.closeInventory();
            }
            if (itemname.contains("achievements")) {
                Chat.sendMessage(p, "Debug", "open achievements");
                p.closeInventory();
            }
            if (itemname.contains("purchases")) {
                Chat.sendMessage(p, "Debug", "open purchases");
                p.closeInventory();
            }
        }

        if (e.getView().getTitle().contains("Permission Error")) {
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
                p.closeInventory();
            }
        }

        if (e.getView().getTitle().contains("- Profile")) {
            e.setCancelled(true);
        }

        if (e.getView().getTitle().contains("- Rank")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toString().toLowerCase();
            OfflinePlayer target = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getLocalizedName().toString());
            User user = Players.getUser(target.getName());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("back")) {
                openEditUserGUI(p, target);
            }
            if (itemname.contains("guest")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set default");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("default").getPrefix());
                user.setRank(Rank.fromString("default"));
                p.closeInventory();
            }
            if (itemname.contains("silver")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set silver");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("silver").getPrefix());
                user.setRank(Rank.fromString("silver"));
                p.closeInventory();
            }
            if (itemname.contains("gold")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set gold");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("gold").getPrefix());
                user.setRank(Rank.fromString("gold"));
                p.closeInventory();
            }
            if (itemname.contains("platinum+")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum+");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("platinum+").getPrefix());
                user.setRank(Rank.fromString("platinum+"));
                p.closeInventory();
                return;
            }
            if (itemname.contains("platinum")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("platinum").getPrefix());
                user.setRank(Rank.fromString("platinum"));
                p.closeInventory();
            }
            if (itemname.contains("special guest")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set specialguest");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("specialguest").getPrefix());
                user.setRank(Rank.fromString("specialguest"));
                p.closeInventory();
            }
            if (itemname.contains("security")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set security");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("security").getPrefix());
                user.setRank(Rank.fromString("security"));
                p.closeInventory();
            }
            if (itemname.contains("character")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set character");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("character").getPrefix());
                user.setRank(Rank.fromString("character"));
                p.closeInventory();
            }
            if (itemname.contains("photopass")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set photopass");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("photopass").getPrefix());
                user.setRank(Rank.fromString("photopass"));
                p.closeInventory();
            }
            if (itemname.contains("cast member")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set castmember");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("castmember").getPrefix());
                user.setRank(Rank.fromString("castmember"));
                p.closeInventory();
            }
            if (itemname.contains("coordinator")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set coordinator");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("coordinator").getPrefix());
                user.setRank(Rank.fromString("coordinator"));
                p.closeInventory();
            }
            if (itemname.contains("developer")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set developer");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("developer").getPrefix());
                user.setRank(Rank.fromString("developer"));
                p.closeInventory();
            }
            if (itemname.contains("manager")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set manager");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Rank.fromString("manager").getPrefix());
                user.setRank(Rank.fromString("manager"));
                p.closeInventory();
            }

        }

        if (e.getView().getTitle().contains("- Security")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            Player target = Bukkit.getPlayer(UUID.fromString(e.getCurrentItem().getItemMeta().getLocalizedName()));
            User user = Players.getUser(target.getName());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }

            if (itemname.contains("warn")) {
                openWarn(p, target);
            }

            if (itemname.contains("mute")) {
                openMute(p, target);
            }

            if (itemname.contains("kick")) {
                openKick(p, target);
            }

            if (itemname.contains("ban")) {
                openBan(p, target);
            }

        }

        if (e.getView().getTitle().contains("- Warn")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            Player target = Bukkit.getPlayer(UUID.fromString(e.getCurrentItem().getItemMeta().getLocalizedName()));
            User user = Players.getUser(target.getName());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("back")) {
                openSecurity(p, target);
            }
            if (itemname.contains("reason")) {
                e.setCancelled(true);
            }

        }

        if (e.getView().getTitle().contains("- Mute")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            Player target = Bukkit.getPlayer(UUID.fromString(e.getCurrentItem().getItemMeta().getLocalizedName()));
            User user = Players.getUser(target.getName());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("back")) {
                openSecurity(p, target);
            }

        }

        if (e.getView().getTitle().contains("- Kick")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            Player target = Bukkit.getPlayer(UUID.fromString(e.getCurrentItem().getItemMeta().getLocalizedName()));
            User user = Players.getUser(target.getName());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("back")) {
                openSecurity(p, target);
            }

        }

        if (e.getView().getTitle().contains("- Ban")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            Player target = Bukkit.getPlayer(UUID.fromString(e.getCurrentItem().getItemMeta().getLocalizedName()));
            User user = Players.getUser(target.getName());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("back")) {
                openSecurity(p, target);
            }

        }
    }

    public static void addItem(Inventory inv, Material type, String name, ArrayList lore, String notes, Integer location) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lorelist = lore;

        meta.setLore(lorelist);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE);
        meta.setLocalizedName(notes);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        item.setItemMeta(meta);
        inv.setItem(location, item);
    }

    public static ItemStack getItem(Material type, String name, ArrayList lore, String notes) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lorelist = lore;

        meta.setLore(lorelist);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE);
        meta.setLocalizedName(notes);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        item.setItemMeta(meta);
        return item;
    }

    public static void openEditUserGUI(Player p, OfflinePlayer target) {
        currentGUI = "editUser";
        Inventory editUser = Bukkit.getServer().createInventory(p, 36, "§0Edit User §b" + target.getName().toString());

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        ArrayList<String> profilelore = new ArrayList<String>();
        profilelore.add(" ");
        profilelore.add("§bView the user's profile");
        profilelore.add(" ");

        sm.setLore(profilelore);
        sm.setDisplayName("§b§l" + target.getName().toString() + "§a§l's Profile");
        sm.setLocalizedName(target.getName().toString());

        profile.setItemMeta(sm);
        editUser.setItem(4, profile);

        addItem(editUser, Material.REDSTONE_TORCH, "&a&lUpdate Rank", Lists.newArrayList(" ", "§bEdit the user's rank", " "), target.getName().toString(), 19);

        addItem(editUser, Material.CHEST, "&a&lBackpack", Lists.newArrayList(" ", "§bView the user's backpack", " "), target.getName().toString(), 20);

        addItem(editUser, Material.MINECART, "&a&lRides", Lists.newArrayList(" ", "§bView the user's ride counters", " "), target.getName().toString(), 21);

        addItem(editUser, Material.NAME_TAG, "&a&lFriends", Lists.newArrayList(" ", "§bView the users friends", " "), target.getName().toString(), 22);

        addItem(editUser, Material.BARRIER, "&a&lPunishments", Lists.newArrayList(" ", "§bView the user's past & current punishments", " "), target.getName().toString(), 23);

        addItem(editUser, Material.ENCHANTED_GOLDEN_APPLE, "&a&lAchievements", Lists.newArrayList(" ", "§bView the user's achievements", " "), target.getName().toString(), 24);

        addItem(editUser, Material.DIAMOND, "&a&lPurchases", Lists.newArrayList(" ", "§bView the user's shop purchases & hotel room bookings", " "), target.getName().toString(), 25);


        p.openInventory(editUser);
    }

    public static void openPermErrorGUI(Player p) {
        currentGUI = "permError";
        Inventory permError = Bukkit.getServer().createInventory(p, 9, "§cPermission Error");

        addItem(permError, Material.REDSTONE_BLOCK, "&c&lPermission Error!", Lists.newArrayList(" ", "§cYou do not have access to this menu!", " "), null, 4);

        p.openInventory(permError);
    }

    public static void openRankGUI(Player p, OfflinePlayer target) {
        if (!Permissions.checkPermission(p, "core.updaterank")) {
            openPermErrorGUI(p);
            return;
        }
        currentGUI = "rank";
        Inventory editUser = Bukkit.getServer().createInventory(p, 54, "§0Edit User §b" + target.getName().toString() + "§0 - Rank");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        ArrayList<String> profilelore = new ArrayList<String>();
        profilelore.add(" ");
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getOfflineRank(target).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        profilelore.add("§bCurrent rank: " + prefix);
        profilelore.add(" ");

        sm.setLore(profilelore);
        sm.setDisplayName("§aChanging §b§l" + target.getName().toString() + "§a's rank");
        sm.setLocalizedName(target.getName().toString());

        profile.setItemMeta(sm);
        editUser.setItem(4, profile);

        addItem(editUser, Material.RED_MUSHROOM, "&f[&eGuest&f]", Lists.newArrayList(" ", "§bSet the user's rank to §eGuest", " "), target.getName().toString(), 19);

        addItem(editUser, Material.IRON_INGOT, "&f[&6Silver&f]", Lists.newArrayList(" ", "§bSet the user's rank to §6Silver", " "), target.getName().toString(), 20);

        addItem(editUser, Material.GOLD_INGOT, "&f[&6Gold&f]", Lists.newArrayList(" ", "§bSet the user's rank to §6Gold", " "), target.getName().toString(), 21);

        addItem(editUser, Material.SLIME_BALL, "&f[&6Platinum&f]", Lists.newArrayList(" ", "§bSet the user's rank to §6Platinum", " "), target.getName().toString(), 22);

        addItem(editUser, Material.DIAMOND, "&f[&6Platinum+&f]", Lists.newArrayList(" ", "§bSet the user's rank to §6Platinum+", " "), target.getName().toString(), 23);

        addItem(editUser, Material.REDSTONE, "&0[&4Special Guest&0]", Lists.newArrayList(" ", "§bSet the user's rank to §4Special Guest", " "), target.getName().toString(), 24);

        addItem(editUser, Material.REDSTONE_TORCH, "&f[&4Security&f]", Lists.newArrayList(" ", "§bSet the user's rank to §4Security", " "), target.getName().toString(), 25);

        addItem(editUser, Material.LEATHER_HELMET, "&f[&6Character&f]", Lists.newArrayList(" ", "§bSet the user's rank to §3Character", " "), target.getName().toString(), 28);

        addItem(editUser, Material.HOPPER, "&f[&3Photopass&f]", Lists.newArrayList(" ", "§bSet the user's rank to §3Photopass", " "), target.getName().toString(), 29);

        addItem(editUser, Material.ANVIL, "&f[&3Cast Member&f]", Lists.newArrayList(" ", "§bSet the user's rank to §3Cast Member", " "), target.getName().toString(), 30);

        addItem(editUser, Material.PEONY, "&f[&cCoordinator&f]", Lists.newArrayList(" ", "§bSet the user's rank to §cCoordinator", " "), target.getName().toString(), 31);

        addItem(editUser, Material.REDSTONE, "&f[&5Developer&f]", Lists.newArrayList(" ", "§bSet the user's rank to §5Developer", " "), target.getName().toString(), 32);

        addItem(editUser, Material.REDSTONE_BLOCK, "&f[&aManager&f]", Lists.newArrayList(" ", "§bSet the user's rank to §aManager", " "), target.getName().toString(), 33);



        addItem(editUser, Material.BARRIER, "&c&lBack", Lists.newArrayList(" ", "§cGo back to edit user", " "), target.getName().toString(), 45);


        p.openInventory(editUser);
    }

    public static void openProfile(Player p, OfflinePlayer target) {
        currentGUI = "profile";
        Inventory userProfile = Bukkit.getServer().createInventory(p, 27, "§b" + target.getName().toString() + "§0 - Profile");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        sm.setDisplayName("§b§l" + target.getName().toString() + "§a's Profile");

        sm.setLocalizedName(target.getUniqueId().toString());
        profile.setItemMeta(sm);
        userProfile.setItem(4, profile);

        addItem(userProfile, Material.GOLD_INGOT, "&bBalance", Lists.newArrayList(" ", "§a$" + MySQL.getOfflineBalance(target)), target.getUniqueId().toString(), 11);

        addItem(userProfile, Material.ANVIL, "&bRank", Lists.newArrayList(" ", Chat.sendColorFree(Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix())), target.getUniqueId().toString(), 12);

        if (MySQL.checkPlayerOnline(target.getUniqueId().toString())) {
            addItem(userProfile, Material.REDSTONE_TORCH, "&bStatus", Lists.newArrayList(" ", "§aOnline"), target.getName().toString(), 13);
        } else {
            addItem(userProfile, Material.LEVER, "&bStatus", Lists.newArrayList( " ", "§7Offline"), target.getName().toString(), 13);
        }

        String firstJoin = CalendarUtils.ConvertMilliSecondsToFormattedDate(MySQL.getPlayerDataUUID(target.getUniqueId().toString(), "first_join"));

        addItem(userProfile, Material.SLIME_BALL, "&bFirst Join", Lists.newArrayList(" ", "§a" + firstJoin), target.getName().toString(), 14);

        addItem(userProfile, Material.LEGACY_REDSTONE_COMPARATOR, "&bFriend Count", Lists.newArrayList(" ", "§a500"), target.getName().toString(), 15);

        if (Core.getUser(p.getName()).getRank().getRankLadder() >= 6) {
            addItem(userProfile, Material.BOOK, "&bMessage", Lists.newArrayList(" ", "§aSend the player a message"), target.getName().toString(), 20);

            addItem(userProfile, Material.GOLD_INGOT, "&bSend Money", Lists.newArrayList(" ", "§aSend the player money"), target.getName().toString(), 21);

            addItem(userProfile, Material.COMPASS, "&bSend Friend Request", Lists.newArrayList(" ", "§aSend the player a friend request"), target.getName().toString(), 22);

            addItem(userProfile, Material.BARRIER, "&bManage Security", Lists.newArrayList(" ", "§aOpen security menu for the player"), target.getName().toString(), 23);

            addItem(userProfile, Material.SHEARS, "&bEdit User", Lists.newArrayList(" ", "§aOpen edit menu for the player"), target.getName().toString(), 24);
        } else {
            addItem(userProfile, Material.BOOK, "&bMessage", Lists.newArrayList(" ", "§aSend the player a message"), target.getName().toString(), 21);

            addItem(userProfile, Material.GOLD_INGOT, "&bSend Money", Lists.newArrayList(" ", "§aSend the player money"), target.getName().toString(), 22);

            addItem(userProfile, Material.COMPASS, "&bSend Friend Request", Lists.newArrayList(" ", "§aSend the player a friend request"), target.getName().toString(), 23);
        }




        p.openInventory(userProfile);
    }

    public static void openSecurity(Player p, Player target) {
        currentGUI = "security";
        Inventory userSecurity = Bukkit.getServer().createInventory(p, 18, "§b" + target.getName().toString() + "§0 - Security");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        sm.setDisplayName("§b§l" + target.getName().toString());
        sm.setLore(Lists.newArrayList(" ", "" + prefix));

        sm.setLocalizedName(target.getUniqueId().toString());
        profile.setItemMeta(sm);
        userSecurity.setItem(4, profile);

        addItem(userSecurity, Material.OAK_SIGN, "&bWarn", Lists.newArrayList(" ", "§aOpen warn user menu"), target.getUniqueId().toString(), 10);

        addItem(userSecurity, Material.HOPPER, "&bKick", Lists.newArrayList(" ", "§aOpen kick user menu"), target.getUniqueId().toString(), 12);

        addItem(userSecurity, Material.JUKEBOX, "&bMute", Lists.newArrayList(" ", "§aOpen mute user menu"), target.getUniqueId().toString(), 14);

        addItem(userSecurity, Material.BARRIER, "&bBan", Lists.newArrayList(" ", "§aOpen ban user menu"), target.getUniqueId().toString(), 16);





        p.openInventory(userSecurity);
    }

    public static void openWarn(Player p, Player target) {
        currentGUI = "warn";
        Inventory userSecurityWarn = Bukkit.getServer().createInventory(p, 18, "§b" + target.getName().toString() + "§0 - Warn");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        sm.setDisplayName("§b§l" + target.getName().toString());
        sm.setLore(Lists.newArrayList(" ", "" + prefix));

        sm.setLocalizedName(target.getUniqueId().toString());
        profile.setItemMeta(sm);
        userSecurityWarn.setItem(4, profile);

        addItem(userSecurityWarn, Material.WRITABLE_BOOK, "&b&lEnter Reason", Lists.newArrayList(" ", "§aEnter a warn reason"), target.getUniqueId().toString(), 13);

        addItem(userSecurityWarn, Material.BARRIER, "&c&lBack", Lists.newArrayList(" ", "§cGo back to user security menu", " "), target.getUniqueId().toString(), 9);





        p.openInventory(userSecurityWarn);
    }

    public static void openConfirmWarn(Player p, Player target, String reason) {
        currentGUI = "warnConfirm";
        Inventory userSecurityWarn = Bukkit.getServer().createInventory(p, 18, "§b" + target.getName().toString() + "§0 - Confirm Warn");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        sm.setDisplayName("§b§l" + target.getName().toString());
        sm.setLore(Lists.newArrayList(" ", "" + prefix));

        sm.setLocalizedName(target.getUniqueId().toString());
        profile.setItemMeta(sm);
        userSecurityWarn.setItem(4, profile);

        addItem(userSecurityWarn, Material.GREEN_WOOL, "&a&lYes, Warn", Lists.newArrayList(" ", "§aWarns the player"), target.getUniqueId().toString(), 11);
        addItem(userSecurityWarn, Material.BOOK, "&b&lAre you sure?", Lists.newArrayList(" ", "§bWarn Reason: §a" + reason), target.getUniqueId().toString(), 13);
        addItem(userSecurityWarn, Material.REDSTONE_BLOCK, "&c&lNo, Cancel", Lists.newArrayList(" ", "§cCancel the punishment"), target.getUniqueId().toString(), 15);

        addItem(userSecurityWarn, Material.BARRIER, "&c&lBack", Lists.newArrayList(" ", "§cGo back to reason menu", " "), target.getUniqueId().toString(), 9);





        p.openInventory(userSecurityWarn);
    }

    public static void openKick(Player p, Player target) {
        currentGUI = "kick";
        Inventory userSecurityWarn = Bukkit.getServer().createInventory(p, 18, "§b" + target.getName().toString() + "§0 - Kick");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        sm.setDisplayName("§b§l" + target.getName().toString());
        sm.setLore(Lists.newArrayList(" ", "" + prefix));

        sm.setLocalizedName(target.getUniqueId().toString());
        profile.setItemMeta(sm);
        userSecurityWarn.setItem(4, profile);

        addItem(userSecurityWarn, Material.WRITABLE_BOOK, "&b&lEnter Reason", Lists.newArrayList(" ", "§aEnter a kick reason", " ", "§e§lPlease warn before kicking!"), target.getUniqueId().toString(), 13);

        addItem(userSecurityWarn, Material.BARRIER, "&c&lBack", Lists.newArrayList(" ", "§cGo back to user security menu", ""), target.getUniqueId().toString(), 9);





        p.openInventory(userSecurityWarn);
    }

    public static void openMute(Player p, Player target) {
        currentGUI = "mute";
        Inventory userSecurityWarn = Bukkit.getServer().createInventory(p, 27, "§b" + target.getName().toString() + "§0 - Mute");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        sm.setDisplayName("§b§l" + target.getName().toString());
        sm.setLore(Lists.newArrayList(" ", "" + prefix));

        sm.setLocalizedName(target.getUniqueId().toString());
        profile.setItemMeta(sm);
        userSecurityWarn.setItem(4, profile);

        addItem(userSecurityWarn, Material.PAPER, "&b&lChat Offences", Lists.newArrayList(" ", "§aVarious chat offences"), target.getUniqueId().toString(), 11);
        addItem(userSecurityWarn, Material.GREEN_DYE, "&a7 day temp mute", Lists.newArrayList(" ", "§a- §bSharing personal information", "§a- §bRole playing", "§e§lOr: after second warning"), target.getUniqueId().toString(), 12);
        addItem(userSecurityWarn, Material.YELLOW_DYE, "&630 day temp mute", Lists.newArrayList(" ", "§a- §bSwearing in chat", "§a- §bAdvertising", "§e§lOr: after 7 day temp mute"), target.getUniqueId().toString(), 13);
        addItem(userSecurityWarn, Material.RED_DYE, "&cPerm mute", Lists.newArrayList(" ", "§a- §bRacial Slurs", "§a- §bOver the top inappropriate chat", "§e§lOr: after all temp mutes"), target.getUniqueId().toString(), 14);
        addItem(userSecurityWarn, Material.CLOCK, "&c&lTime Mute", Lists.newArrayList(" ", "§e§lEnter the amount of time to mute a user"), target.getUniqueId().toString(), 16);
        addItem(userSecurityWarn, Material.REDSTONE_BLOCK, "&c&lPerm mute", Lists.newArrayList(" ", "§e§lOffense requires immediate perm mute"), target.getUniqueId().toString(), 17);

        addItem(userSecurityWarn, Material.BARRIER, "&c&lBack", Lists.newArrayList(" ", "§cGo back to user security menu", " "), target.getUniqueId().toString(), 18);





        p.openInventory(userSecurityWarn);
    }

    public static void openBan(Player p, Player target) {
        currentGUI = "ban";
        Inventory userSecurityWarn = Bukkit.getServer().createInventory(p, 54, "§b" + target.getName().toString() + "§0 - Ban");

        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) profile.getItemMeta();
        sm.setOwner(target.getName().toString());
        String prefix = null;
        try {
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getRankUUIDDatabase(target.getUniqueId().toString()).getPrefix());
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        sm.setDisplayName("§b§l" + target.getName().toString());
        sm.setLore(Lists.newArrayList(" ", "" + prefix));

        sm.setLocalizedName(target.getUniqueId().toString());
        profile.setItemMeta(sm);
        userSecurityWarn.setItem(4, profile);

        addItem(userSecurityWarn, Material.PAPER, "&b&lChat Offences", Lists.newArrayList(" ", "§aVarious chat offences"), target.getUniqueId().toString(), 11);
        addItem(userSecurityWarn, Material.GREEN_DYE, "&a7 day temp ban", Lists.newArrayList(" ", "§a- §bSharing personal information", "§a- §bRole playing", "§e§lOr: after perm mute"), target.getUniqueId().toString(), 20);
        addItem(userSecurityWarn, Material.YELLOW_DYE, "&630 day temp ban", Lists.newArrayList(" ", "§a- §bSwearing in chat", "§a- §bAdvertising", "§e§lOr: after perm mute and 7 day temp ban"), target.getUniqueId().toString(), 29);
        addItem(userSecurityWarn, Material.RED_DYE, "&cPerm Ban", Lists.newArrayList(" ", "§a- §bRacial Slurs", "§a- §bOver the top inappropriate chat", "§e§lOr: after perm mute and all temp bans"), target.getUniqueId().toString(), 38);

        addItem(userSecurityWarn, Material.DIAMOND_PICKAXE, "&b&lGame Modifications", Lists.newArrayList(" ", "§aVarious game modifications"), target.getUniqueId().toString(), 13);
        addItem(userSecurityWarn, Material.GREEN_DYE, "&a7 day temp ban", Lists.newArrayList(" ", "§a- §bFly Hacks", "§a- §bX-Ray", "§e§lOr: after second kick"), target.getUniqueId().toString(), 22);
        addItem(userSecurityWarn, Material.YELLOW_DYE, "&630 day temp ban", Lists.newArrayList(" ", "§a- §bFreecam", "§a- §bSpam bots", "§e§lOr: after second kick and 7 day temp ban"), target.getUniqueId().toString(), 31);
        addItem(userSecurityWarn, Material.RED_DYE, "&cPerm Ban", Lists.newArrayList(" ", "§a- §bDDOS'ing", "§a- §bUUID Spoofing", "§e§lOr: after second kick and all temp bans"), target.getUniqueId().toString(), 40);

        addItem(userSecurityWarn, Material.BARRIER, "&b&lGeneral Rules", Lists.newArrayList(" ", "§aVarious general rules"), target.getUniqueId().toString(), 15);
        addItem(userSecurityWarn, Material.GREEN_DYE, "&a7 day temp ban", Lists.newArrayList(" ", "§a- §bWorld Downloading", "§a- §bLeaking private information", "§e§lOr: after second kick"), target.getUniqueId().toString(), 24);
        addItem(userSecurityWarn, Material.YELLOW_DYE, "&630 day temp ban", Lists.newArrayList(" ", "§a- §bBan Envading", "§a- §bSpam accounts", "§e§lOr: after second kick and 7 day temp ban"), target.getUniqueId().toString(), 33);
        addItem(userSecurityWarn, Material.RED_DYE, "&cPerm ban", Lists.newArrayList(" ", "§a- §bAbusing exploits" , "§e§lOr: after second kick and all bans"), target.getUniqueId().toString(), 42);

        addItem(userSecurityWarn, Material.REDSTONE_BLOCK, "&c&lPerm ban", Lists.newArrayList(" ", "§e§lOffense requires immediate perm ban"), target.getUniqueId().toString(), 35);

        addItem(userSecurityWarn, Material.BARRIER, "&c&lBack", Lists.newArrayList(" ", "§cGo back to user security menu", " "), target.getUniqueId().toString(), 45);





        p.openInventory(userSecurityWarn);
    }

}
