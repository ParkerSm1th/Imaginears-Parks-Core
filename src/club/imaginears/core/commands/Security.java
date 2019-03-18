package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.GUIs;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Security implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.security")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/security (Player)");
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/security (Online Player) - Please keep in mind you must be on the same server as a specified player");
                    return true;
                }
                GUIs.openSecurity(p, target);
            }

        }

        return true;
    }

}
