package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.GUIs;
import club.imaginears.core.utils.Permissions;
import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiveCustomItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.givecustomitem")) {
            if (args.length > 3 || args.length < 3) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/givecustomitem (Player) (Custom Item Name) (Count)");
            }
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/givecustomitem (Online Player) (Custom Item Name) (Count)");
                    return true;
                }
                String name = args[1];
                int count = Integer.parseInt(args[2]);
                if (count == 0) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/givecustomitem (Player) (Custom Item Name) (Count)");
                    return true;
                }
                switch (name) {
                    case "mickeyears":
                        ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL);
                        item.setAmount(count);
                        ItemMeta meta = item.getItemMeta();
                        ArrayList<String> lorelist = Lists.newArrayList(" ", "§7Your very own pair of Mickey Mouse Ears");

                        meta.setLore(lorelist);
                        meta.setLocalizedName(target.getUniqueId().toString());
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS);

                        meta.setUnbreakable(true);
                        ((Damageable) meta).setDamage(1);
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Mickey Mouse Ears"));

                        item.setItemMeta(meta);
                        target.getInventory().addItem(item);
                        break;
                    case "minnieears":
                        ItemStack item2 = new ItemStack(Material.DIAMOND_SHOVEL);
                        item2.setAmount(count);
                        ItemMeta meta2 = item2.getItemMeta();
                        ArrayList<String> lorelist2 = Lists.newArrayList(" ", "§7Your very own pair of Minnie Mouse Ears");

                        meta2.setLore(lorelist2);
                        meta2.setLocalizedName(target.getUniqueId().toString());
                        meta2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS);
                        meta2.setUnbreakable(true);
                        ((Damageable) meta2).setDamage(2);
                        meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&dMinnie Mouse Ears"));

                        item2.setItemMeta(meta2);
                        target.getInventory().addItem(item2);
                        break;
                    case "purpleears":
                        ItemStack item3 = new ItemStack(Material.DIAMOND_SHOVEL);
                        item3.setAmount(count);
                        ItemMeta meta3 = item3.getItemMeta();
                        ArrayList<String> lorelist3 = Lists.newArrayList(" ", "§7Your very own pair of Purple Minnie Mouse Ears");

                        meta3.setLore(lorelist3);
                        meta3.setLocalizedName(target.getUniqueId().toString());
                        meta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS);
                        meta3.setUnbreakable(true);
                        ((Damageable) meta3).setDamage(3);
                        meta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Purple Minnie Mouse Ears"));

                        item3.setItemMeta(meta3);
                        target.getInventory().addItem(item3);
                        break;
                    case "bdaypin":
                        ItemStack item4 = new ItemStack(Material.LIGHT_BLUE_DYE);
                        item4.setAmount(count);
                        ItemMeta meta4 = item4.getItemMeta();
                        ArrayList<String> lorelist4 = Lists.newArrayList(" ", "§7Happy birthday! This is your very own Birthday Pin");

                        meta4.setLore(lorelist4);
                        meta4.setLocalizedName(target.getUniqueId().toString());
                        meta4.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS);
                        meta4.setUnbreakable(true);
                        meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bHappy Birthday Pin"));

                        item4.setItemMeta(meta4);
                        target.getInventory().addItem(item4);
                        break;
                    default:
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "That is not a valid custom item");
                }
            }

        }

        return true;
    }

}
