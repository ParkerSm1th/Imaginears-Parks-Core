package club.imaginears.core.utils;

import club.imaginears.core.Core;
import club.imaginears.core.commands.Build;
import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;

public class InventoryManager implements Listener {

    public static FileConfiguration inventories = FileAccess.getInventories();

    public static void saveFile() {
        try {
            Core.getInstance().inventories.save(Core.getInstance().inventoriesFile);
            Console.Log("saved inventories file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addItem(PlayerInventory inv, Material type, String name, ArrayList lore, String notes, Integer location) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lorelist = lore;

        meta.setLore(lorelist);
        meta.setLocalizedName(notes);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        item.setItemMeta(meta);
        inv.setItem(location, item);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (Build.buildMode.contains(p.getName())) return;
        if (e.getClickedInventory().getType() == p.getInventory().getType()) {
            String itemname = e.getCurrentItem().getItemMeta().getDisplayName().toString().toLowerCase();
            if (e.getCurrentItem().getItemMeta().hasLocalizedName()) {
                e.setCancelled(true);

            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (Build.buildMode.contains(p.getName())) return;
        String itemname = e.getItem().getItemMeta().getDisplayName().toString().toLowerCase();
        if (e.getItem().getItemMeta().hasLocalizedName()) {
            e.setCancelled(true);
            Chat.sendMessage(p, "Pixie Dust", "Currently we are working on this!");
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        if (Build.buildMode.contains(p.getName())) return;
        String itemname = e.getItemDrop().getItemStack().getItemMeta().getDisplayName().toString().toLowerCase();
        if (e.getItemDrop().getItemStack().getItemMeta().hasLocalizedName()) {
            e.setCancelled(true);
        }
    }

    public static void loadPlayInventory(Player p) {
        p.getInventory().clear();
        if (inventories.isSet(p.getUniqueId().toString() + ".play")) {
            try {
                p.getInventory().setContents(InventoryStringDeSerializer.itemStackArrayFromBase64(inventories.getString(p.getUniqueId().toString() + ".play")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            addItem(p.getInventory(), Material.GRAY_DYE, "§b" + p.getName() + "§a's MagicBand", Lists.newArrayList(" ", "§7From here you can access everything", "§7you need to enjoy your time on §bImaginears", " "), p.getUniqueId().toString(), 0);
            addItem(p.getInventory(), Material.ENCHANTED_BOOK, "§b" + p.getName() + "§a's Autograph Book", Lists.newArrayList(" ", "§7Have your favourite characters or", "§7staff sign your autograph book!", " "), p.getUniqueId().toString(), 1);
            addItem(p.getInventory(), Material.CHEST, "§b" + p.getName() + "§a's Backpack", Lists.newArrayList(" ", "§7Store your keepsake items in here!", " "), p.getUniqueId().toString(), 2);
            addItem(p.getInventory(), Material.GLASS_PANE, "§7Reserved Slot", Lists.newArrayList(" ", "§7Reserved for ride items or event items", " "), p.getUniqueId().toString(), 3);
            addItem(p.getInventory(), Material.GLASS_PANE, "§7Reserved Slot", Lists.newArrayList(" ", "§7Reserved for ride items or event items", " "), p.getUniqueId().toString(), 4);
            for (int i = 9; i<37; i++) {
                addItem(p.getInventory(), Material.GRAY_STAINED_GLASS_PANE, "§cUse your backpack for storage", Lists.newArrayList(" ", "§7Please use your backpack for storage!", " "), p.getUniqueId().toString(), i);
            }
        }
    }

    public static void savePlayInventory(Player p) {
        inventories.set(p.getUniqueId().toString() + ".play", InventoryStringDeSerializer.toBase64(p.getInventory()));
        saveFile();
    }

    public static void loadBuildInventory(Player p) {
        savePlayInventory(p);
        p.getInventory().clear();
        if (inventories.isSet(p.getUniqueId().toString() + ".build")) {
            try {
                p.getInventory().setContents(InventoryStringDeSerializer.itemStackArrayFromBase64(inventories.getString(p.getUniqueId().toString() + ".build")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            addItem(p.getInventory(), Material.COMPASS, "§bCM Compass", Lists.newArrayList(" ", "§7Helps you get places quicker", " "), p.getUniqueId().toString(), 0);
            addItem(p.getInventory(), Material.WOODEN_AXE, "§bW/E Wand", Lists.newArrayList(" ", "§7W/E wand to save you a command.", " "), p.getUniqueId().toString(), 1);
        }
    }

    public static void saveBuildInventory(Player p) {
        inventories.set(p.getUniqueId().toString() + ".build", InventoryStringDeSerializer.toBase64(p.getInventory()));
        saveFile();
    }


}
