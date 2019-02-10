package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.MySQL;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.pay")) {
            if (args.length > 2 || args.length < 2) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/pay (Player) (Amount)");
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/pay (Online Player) (Amount)");
                    return true;
                }
                Float amount = Float.parseFloat(args[1]);
                User targetUser = Core.getUser(target.getUniqueId());
                if (u.getBalance() < amount) {
                    Chat.sendError(p, Chat.ChatErrors.COMMON, "You do not have &b$" + amount + "&a!");
                    return true;
                }
                u.subtractFromBalance(amount);
                targetUser.addToBalance(amount);
                MySQL.logTransaction(p.getUniqueId().toString(), target.getUniqueId().toString(), amount);
                Chat.sendMessage(p, "Economy", "You have paid &b" + target.getName() + "&a, &b$" + amount);
                Chat.sendMessage(target, "Economy", "You have been given &b$" + amount + " &afrom &b" + target.getName());
            }

        }

        return true;
    }

}
