package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Vanish implements CommandExecutor {

    public static ArrayList<String> vanished = new ArrayList<String>();

    public void vanish(Player p) {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            if (pls.hasPermission("core.vanish")) {
                if (pls != p) {
                    Chat.sendMessage(pls, "Staff", "&b" + p.getName() + " &ahas vanished");
                }
            } else {
                pls.hidePlayer(p);
            }
        }

        vanished.add(p.getName());
        Chat.sendMessage(p, "Staff", "You are now vanished");
    }

    public void unVanish(Player p) {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            if (pls.hasPermission("core.vanish")) {
                if (pls != p) {
                    Chat.sendMessage(pls, "Staff", "&b" + p.getName() + " &ahas unvanished");
                }
            } else {
                pls.showPlayer(p);
            }
        }
        vanished.remove(p.getName());
        Chat.sendMessage(p, "Staff", "You are no longer vanished");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        if (Permissions.checkPermissionMsg(p, "core.vanish")) {
            if (args.length == 0) {
                if (vanished.contains(p.getName())) {
                    unVanish(p);
                } else {
                    vanish(p);
                }
            }

            if (args.length == 1) {
                if (Permissions.checkPermission(p, "core.vanishothers")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        Chat.sendMessage(p, "Error", "That player is not online.");
                        return true;
                    }
                    if (vanished.contains(target.getName())) {
                        unVanish(target);
                    } else {
                        vanish(target);
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/vanish");
                }
            }

            if (args.length > 1) {
                if (Permissions.checkPermission(p, "core.vanishothers")) {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/vanish or /vanish (Player)");
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/vanish");
                }
            }
        }
        return true;
    }

}
