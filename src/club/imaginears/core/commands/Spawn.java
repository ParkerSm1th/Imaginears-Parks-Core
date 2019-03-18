package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Warps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.warp")) {
            if (args.length == 0) {
                p.teleport(Warps.getWarp("ttc"));
                Chat.sendMessage(p, "Warps", "You've arrived at &bttc &a(location)");
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/spawn");
            }

        }

        return true;
    }

}
