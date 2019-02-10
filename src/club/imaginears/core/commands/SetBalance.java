package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.MySQL;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBalance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.setbalance")) {
            if (args.length > 2 || args.length < 2) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/setbalance (User) (Amount)");
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                Float amount = Float.parseFloat(args[1]);
                if (target == null) {
                    OfflinePlayer offtarget = Bukkit.getOfflinePlayer(args[0]);

                    if (!MySQL.checkPlayerExistsOffline(offtarget)) {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "That player has never logged in");
                    }

                    MySQL.setOfflineBalance(offtarget, amount);
                    Chat.sendMessage(p, "Economy", "Set the players balance");
                    return true;
                }
                User targetUser = Core.getUser(target.getUniqueId());
                targetUser.setBalance(amount);
                Chat.sendMessage(p, "Economy", "Set the players balance");
            }

        }

        return true;
    }

}
