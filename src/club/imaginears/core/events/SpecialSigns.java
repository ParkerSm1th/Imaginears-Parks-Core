package club.imaginears.core.events;

import club.imaginears.core.Core;
import club.imaginears.core.objects.Shop;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.*;
import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SpecialSigns implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Console.Log("Placed block", Console.types.DEBUG);
        if (e.getBlockPlaced().getType() == Material.SIGN || e.getBlockPlaced().getType() == Material.WALL_SIGN) {
            Console.Log("Placed sign", Console.types.DEBUG);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (b.getType() == Material.SIGN || b.getType() == Material.WALL_SIGN) {
            Sign sign = (Sign) e.getBlock().getState();
            if (sign.getLine(0).contains("Shop")) {
                Location location = new Location(Bukkit.getWorld(b.getWorld().getName()), b.getX(), b.getY(), b.getZ());
                if (Core.enabledShops.get(location) != null) {
                    Shop shop = Core.enabledShops.get(location);
                    shop.deleteShop();
                    Core.enabledShops.remove(location);
                    Chat.sendMessage(p, "Shops", "That shop has been deleted.");
                } else {
                    if (Core.inProgressShops.get(b.getLocation()) != null) {
                        Core.inProgressShops.remove(b.getLocation());
                        Chat.sendMessage(p, "Shops", "That shop has been deleted (it was in mid setup)");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (p.getGameMode() == GameMode.CREATIVE && e.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (Core.playerOnShop.containsKey(p)) {
            if (b.getType() == Material.CHEST) {
                if (p.isSneaking()) {
                    e.setCancelled(true);
                    Location signLocation = Core.playerOnShop.get(p).getLocation();
                    Shop workingOn = Core.inProgressShops.get(signLocation);
                    Chest chest = ((Chest) b.getState());
                    ArrayList<ItemStack> tempitems = new ArrayList<ItemStack>();
                    for (int i = 0; i < 27; i++) {
                        Console.Log("I! " + i, Console.types.DEBUG);
                        if (chest.getInventory().getItem(i) != null) {
                            Console.Log("Good I! " + i, Console.types.DEBUG);
                            tempitems.add(chest.getInventory().getItem(i));
                        } else {

                        }
                    }
                    ItemStack[] items = tempitems.toArray(new ItemStack[]{});
                    ShopSigns.setupShop(workingOn.getName(), workingOn.getStore(), workingOn.getPrice(), workingOn.getLocation(), items);
                    Core.inProgressShops.remove(signLocation);
                    Core.playerOnShop.remove(p);
                    Chat.sendMessage(p, "Shops", "Deselected shop that you were currently working and setup shop sign");
                }
            } else {
                Core.playerOnShop.remove(p);
                Chat.sendMessage(p, "Shops", "Deselected shop that you were currently working on");
            }
            return;
        }
        if (!p.isSneaking()) {
            if (b.getType() == Material.SIGN || b.getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (sign.getLine(0).contains("Shop")) {
                    Location location = new Location(Bukkit.getWorld(b.getWorld().getName()), b.getX(), b.getY(), b.getZ());
                    if (Core.enabledShops.containsKey(b.getLocation())) {
                        Shop shop = Core.enabledShops.get(b.getLocation());
                        User u = Core.getUser(p.getUniqueId());
                        if (u.getBalance() < shop.getPrice()) {
                            Chat.sendError(p, Chat.ChatErrors.COMMON, "You do not have enough money to make this purchase!");
                        } else {
                            u.subtractFromBalance(shop.getPrice());
                            Chat.sendMessage(p, "Charge", "You have been charged &b$" + shop.getPrice() + " &aby &b" + shop.getStore() + " &aand have been given &b" + shop.getName());
                            MySQL.logTransaction(u.getUniqueId().toString(), shop.getStore(), "CHARGE", shop.getPrice());
                            p.getInventory().addItem(shop.getItems());
                        }
                    }
                    if (Core.inProgressShops.containsKey(b.getLocation())) {
                        Core.playerOnShop.put(p, Core.inProgressShops.get(b.getLocation()));
                        Chat.sendMessage(p, "Shops", "Selected shop, please click the chest with the item(s) a player will be given!");
                    }
                }
            }
        } else {
        }
    }

    @EventHandler
    public void onSign(SignChangeEvent e) {
        Player p = e.getPlayer();
        Console.Log(e.getLine(0), Console.types.DEBUG);
        if (e.getLine(0).contains("Shop")) {
            if (e.getLine(1).length() > 0 && e.getLine(2).length() > 0) {
                Chat.sendMessage(p, "Shops", "Placed shop sign, to finish setting up the shop sign please right click the sign then shift and right click a chest with the item(s) the player will get when they purchase something.");
                String name = e.getLine(1);
                String store = e.getLine(2);
                Float price = Float.parseFloat(e.getLine(3));
                e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&b[Shop]"));
                e.setLine(3, ChatColor.translateAlternateColorCodes('&', "&a$" + e.getLine(3)));
                e.setLine(2, ChatColor.translateAlternateColorCodes('&', "&b" + name));
                e.setLine(1, ChatColor.translateAlternateColorCodes('&', "&9" + store));
                Console.Log(e.getBlock().getLocation().toString(), Console.types.DEBUG);
                Core.inProgressShops.put(e.getBlock().getLocation(), new Shop(e.getBlock().getLocation(), name, store, price, null, null));
            } else {
                Chat.sendError(p, Chat.ChatErrors.COMMON, "To place a shop sign please put Shop on the top line, the name of the item on the second line, the store name on the third line, and the price of the item on the fourth line");
                e.getBlock().setType(Material.AIR);
            }
        }
    }
}
