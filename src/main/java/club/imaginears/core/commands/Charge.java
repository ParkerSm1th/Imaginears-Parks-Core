package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.Transaction;
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

import static club.imaginears.core.objects.Transaction.transactionType.CHARGE;

public class Charge implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.charge")) {
            if (args.length > 3 || args.length < 3) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/charge (Player) (Store Name) (Amount)");
            }
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/charge (Online Player) (Store Name) (Amount)");
                    return true;
                }

                User targetUser = Core.getUser(target.getUniqueId());

                Float balance = targetUser.getBalance();
                String storeName = args[1];
                Float amount = Float.parseFloat(args[2]);
                if (!(balance >= amount)) {
                    Chat.sendError(p, Chat.ChatErrors.COMMON, "That player does not have sufficient funds.");
                    Chat.sendError(target, Chat.ChatErrors.COMMON, "You do not have enough money to make this purchase!");
                    return true;
                }

                Transaction trans = new Transaction(CHARGE, u.getUniqueId().toString(), storeName, amount);
                trans.process();
                trans.logTransaction();
                Chat.sendMessage(p, "Charge", "The user has been charged.");
                Chat.sendMessage(target, "Charge", "You have been charged &b$" + amount + " &aby &b" + storeName);
            }

        }

        return true;
    }

}
