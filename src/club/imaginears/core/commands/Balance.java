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

public class Balance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.balance")) {
            if (args.length == 0) {
                Chat.sendMessage(p, "Economy", "Your current balance is: &b$" + u.getBalance());
                MySQL.userSQLGrab(u);
            }
            if (args.length == 1) {
                if (Permissions.checkPermission(p, "core.balanceothers")) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    if (MySQL.checkPlayerExistsOffline(target)) {
                        Chat.sendMessage(p, "Economy", "Their current balance is: &b$" + MySQL.getOfflineBalance(target));
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "That player has never logged in to Imaginears");
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/balance");
                }

            }
            if (args.length > 1 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/balance");
            }

        }

        return true;
    }

}
