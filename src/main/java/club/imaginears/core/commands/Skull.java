package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Skull implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.skull")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/skull (Player Name)");
            }
            if (args.length == 1) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                ItemStack newskull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) newskull.getItemMeta();
                sm.setOwner(target.getName());
                sm.setDisplayName("§a" + target.getName() + "§a's head");
                newskull.setItemMeta(sm);
                p.getInventory().addItem(newskull);
                Chat.sendMessage(p, "Staff", "You've been given &b" + target.getName() + "&a's head");
            }

        }

        return true;
    }

}
