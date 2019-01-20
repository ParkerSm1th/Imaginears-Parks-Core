package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Warps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;


        if (Permissions.checkPermissionMsg(p, "core.setwarp")) {
            if (args.length > 3 || args.length < 3) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/setwarp (Warp Name) (Warp Type) (Permission without core. For public warp put guest otherwise put rak name)");
            }

            if (args.length == 3) {
                String name = args[0];
                String type = args[1].toLowerCase();
                String perm = args[2].toLowerCase();

                if (Warps.checkWarp(name)) {
                    Chat.sendError(p, Chat.ChatErrors.COMMON, "That warp already exists. To reset the warp please use /delwarp " + name);
                    return true;
                }

                switch(type) {
                    case "ride":
                        Warps.setWarp(name.toLowerCase(), type, perm, p.getLocation());
                        Chat.sendMessage(p, "Warps", "Successfully created the &b" + type + "&a warp called &b" + name);
                        break;
                    case "show":
                        Warps.setWarp(name.toLowerCase(), type, perm, p.getLocation());
                        Chat.sendMessage(p, "Warps", "Successfully created the &b" + type + "&a warp called &b" + name);
                         break;
                    case "event":
                        Warps.setWarp(name.toLowerCase(), type, perm, p.getLocation());
                        Chat.sendMessage(p, "Warps", "Successfully created the &b" + type + "&a warp called &b" + name);
                        break;
                    case "shop":
                        Warps.setWarp(name.toLowerCase(), type, perm, p.getLocation());
                        Chat.sendMessage(p, "Warps", "Successfully created the &b" + type + "&a warp called &b" + name);
                        break;
                    case "park":
                        Warps.setWarp(name.toLowerCase(), type, perm, p.getLocation());
                        Chat.sendMessage(p, "Warps", "Successfully created the &b" + type + "&a warp called &b" + name);
                        break;
                    case "location":
                        Warps.setWarp(name.toLowerCase(), type, perm, p.getLocation());
                        Chat.sendMessage(p, "Warps", "Successfully created the &b" + type + "&a warp called &b" + name);
                        break;
                    default:
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/setwarp (Warp Name) (Valid Warp Type) (Permission without core. For public warp put guest otherwise put rak name)- Use /warps for a list of warp types");
                        break;
                }
            }

        }

        return true;
    }

}
