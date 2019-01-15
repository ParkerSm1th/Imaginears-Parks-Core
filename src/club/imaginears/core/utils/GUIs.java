package club.imaginears.core.utils;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;

public class GUIs implements Listener {

    public static String currentGUI = null;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getClickedInventory().getName().contains("Edit User")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toString().toLowerCase();
            OfflinePlayer target = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getLocalizedName().toString());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("profile")) {
                Chat.sendMessage(p, "Debug", "open profile" );
                p.closeInventory();
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

        if (e.getClickedInventory().getName().contains("Permission Error")) {
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
                p.closeInventory();
            }
        }

        if (e.getClickedInventory().getName().contains("- Rank")) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toString().toLowerCase();
            OfflinePlayer target = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getLocalizedName().toString());
            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
            if (itemname.contains("back")) {
                openEditUserGUI(p, target);
            }
            if (itemname.contains("guest")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set default");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("default"));
                p.closeInventory();
            }
            if (itemname.contains("silver")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set silver");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("silver"));
                p.closeInventory();
            }
            if (itemname.contains("gold")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set gold");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("gold"));
                p.closeInventory();
            }
            if (itemname.contains("platinum+")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum+");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("platinum+"));
                p.closeInventory();
                return;
            }
            if (itemname.contains("platinum")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("platinum"));
                p.closeInventory();
            }
            if (itemname.contains("special guest")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set specialguest");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("specialguest"));
                p.closeInventory();
            }
            if (itemname.contains("security")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set security");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("security"));
                p.closeInventory();
            }
            if (itemname.contains("character")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set character");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("character"));
                p.closeInventory();
            }
            if (itemname.contains("photopass")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set photopass");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("photopass"));
                p.closeInventory();
            }
            if (itemname.contains("cast member")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set castmember");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("castmember"));
                p.closeInventory();
            }
            if (itemname.contains("coordinator")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set coordinator");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("coordinator"));
                p.closeInventory();
            }
            if (itemname.contains("developer")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set developer");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("developer"));
                p.closeInventory();
            }
            if (itemname.contains("manager")) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set manager");
                Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + Permissions.getTextRankPrefix("manager"));
                p.closeInventory();
            }

        }
    }

    public static void addItem(Inventory inv, Material type, String name, ArrayList lore, String notes, Integer location) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lorelist = lore;

        meta.setLore(lorelist);
        meta.setLocalizedName(notes);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        item.setItemMeta(meta);
        inv.setItem(location, item);
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
            prefix = ChatColor.translateAlternateColorCodes('&', Permissions.getOfflineRankPrefix(target));
        } catch(NullPointerException ex) {
            prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eGuest&f]");
        }

        profilelore.add("§bCurrent rank: " + prefix);
        profilelore.add(" ");

        sm.setLore(profilelore);
        sm.setDisplayName("§aChanging §b§l" + target.getName().toString() + "§a's rank");

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

}
