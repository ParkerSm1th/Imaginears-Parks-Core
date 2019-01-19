package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Warps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.deletewarp")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/delwarp (Warp Name)");
            }
            if (args.length == 1) {
                String name = args[0];
                if (Warps.checkWarp(name)) {
                    Warps.delWarp(name);
                    Chat.sendMessage(p, "Warps", "Successfully deleted the warp");
                } else {
                    Chat.sendError(p, Chat.ChatErrors.COMMON, "That warp does not exist");
                }
            }

        }

        return true;
    }

}
