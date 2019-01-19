package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import me.lucko.luckperms.common.util.TextUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Warps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.warps")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/warps (Type) - Warp types are as follows: ride, show, shop, event, park, and location");
            }
            if (args.length == 1) {
                String type = args[0];
                switch(type) {
                    case "ride":
                        if (!club.imaginears.core.utils.Warps.getWarps(0).isEmpty()) {
                            Chat.sendMessage(p, "Warps", "The current &b" + type + "&a warps are: &b" + String.join("&a, &b", club.imaginears.core.utils.Warps.getWarps(0)));
                        } else {
                            Chat.sendMessage(p, "Warps", "Currently there are no &b" + type + " &awarps");
                        }
                        break;
                    case "show":
                        if (!club.imaginears.core.utils.Warps.getWarps(1).isEmpty()) {
                            Chat.sendMessage(p, "Warps", "The current &b" + type + "&a warps are: &b" + String.join("&a, &b", club.imaginears.core.utils.Warps.getWarps(1)));
                        } else {
                            Chat.sendMessage(p, "Warps", "Currently there are no &b" + type + " &awarps");
                        }
                        break;
                    case "shop":
                        if (!club.imaginears.core.utils.Warps.getWarps(2).isEmpty()) {
                            Chat.sendMessage(p, "Warps", "The current &b" + type + "&a warps are: &b" + String.join("&a, &b", club.imaginears.core.utils.Warps.getWarps(2)));
                        } else {
                            Chat.sendMessage(p, "Warps", "Currently there are no &b" + type + " &awarps");
                        }
                        break;
                    case "event":
                        if (!club.imaginears.core.utils.Warps.getWarps(3).isEmpty()) {
                            Chat.sendMessage(p, "Warps", "The current &b" + type + "&a warps are: &b" + String.join("&a, &b", club.imaginears.core.utils.Warps.getWarps(3)));
                        } else {
                            Chat.sendMessage(p, "Warps", "Currently there are no &b" + type + " &awarps");
                        }
                        break;
                    case "park":
                        if (!club.imaginears.core.utils.Warps.getWarps(4).isEmpty()) {
                            Chat.sendMessage(p, "Warps", "The current &b" + type + "&a warps are: &b" + String.join("&a, &b", club.imaginears.core.utils.Warps.getWarps(4)));
                        } else {
                            Chat.sendMessage(p, "Warps", "Currently there are no &b" + type + " &awarps");
                        }
                        break;
                    case "location":
                        if (!club.imaginears.core.utils.Warps.getWarps(5).isEmpty()) {
                            Chat.sendMessage(p, "Warps", "The current &b" + type + "&a warps are: &b" + String.join("&a, &b", club.imaginears.core.utils.Warps.getWarps(5)));
                        } else {
                            Chat.sendMessage(p, "Warps", "Currently there are no &b" + type + " &awarps");
                        }
                        break;
                    default:
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/warps (Type) - Warp types are as follows: ride, show, shop, event, park, and location");
                        break;
                }
            }

        }

        return true;
    }

}
