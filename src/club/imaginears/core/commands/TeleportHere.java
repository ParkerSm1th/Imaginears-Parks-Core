package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHere implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.tphere")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/tphere (Online player)");
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/tphere (Online player)");
                } else {
                    if (TPToggle.tpoff.contains(target.getName()) && !Permissions.checkPermission(p, "core.tpoverride")) {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "That player has meeting up toggled off.");
                        return true;
                    }
                    target.teleport(p);
                    Chat.sendMessage(p, "Staff", "&b" + target.getName() + " &ahas met up with you");
                }

            }

        }


        return true;
    }

}
