package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.Transaction;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static club.imaginears.core.objects.Transaction.transactionType.*;

public class Transactions implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.transactions")) {
            if (args.length == 0) {
                Transaction trans = u.getMostRecentTransaction();

                if (trans.getType() == CHARGE) {
                    Chat.sendMessage(p, "Economy", "Your most recent transaction was a charge, it was for &b$" + trans.getAmount() + " &aand it was charged by &b" + trans.getOther());
                }

                if (trans.getType() == PAYSEND) {
                    Chat.sendMessage(p, "Economy", "Your most recent transaction was a payment, it was for &b$" + trans.getAmount() + " &aand you payed &b" + Bukkit.getOfflinePlayer(UUID.fromString(trans.getOther())).getName());
                }

                if (trans.getType() == PAYRECIEVE) {
                    Chat.sendMessage(p, "Economy", "Your most recent transaction was a payment, it was for &b$" + trans.getAmount() + " &aand you recieved the payment from &b" + Bukkit.getOfflinePlayer(UUID.fromString(trans.getOther())).getName());
                }

                if (trans.getType() == REWARD) {
                    Chat.sendMessage(p, "Economy", "Your most recent transaction was a reward, it was for &b$" + trans.getAmount());
                }

                if (trans.getType() == ERROR) {
                    Chat.sendMessage(p, "Economy", "You have made no transactions yet!");
                }
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/transactions");
            }

        }

        return true;
    }

}
