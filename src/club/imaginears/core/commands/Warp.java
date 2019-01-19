package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Warps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.warp")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/warp (Warp Name)");
            }
            if (args.length == 1) {
                String name = args[0];
                if (Warps.checkWarp(name)) {
                    if (Permissions.checkPermission(p, "core.warps." + Warps.getWarpPerm(name))) {
                        p.teleport(Warps.getWarp(name));
                        Chat.sendMessage(p, "Warps", "Successfully teleported to &b" + name + " &a(" + Warps.getWarpType(name) + ")");
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "You must have the rank of " + Permissions.getTextRankPrefix(Warps.getWarpPerm(name)) + " &cto use that warp.");
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.COMMON, "That warp does not exist, Try /warps for a list of warps");
                }
            }

        }

        return true;
    }

}
