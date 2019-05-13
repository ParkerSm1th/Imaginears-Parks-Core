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

import static club.imaginears.core.objects.Transaction.transactionType.REWARD;

public class Reward implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.reward")) {
            if (args.length > 2 || args.length < 2) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/reward (Player) (Amount)");
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                Float amount = Float.parseFloat(args[1]);
                if (target == null) { 
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/reward (Online Player) (Amount)");
                    return true;
                }
                User targetUser = Core.getUser(target.getUniqueId());
                Transaction trans = new Transaction(REWARD, targetUser.getUniqueId().toString(), null, amount);
                trans.process();
                Chat.sendMessage(p, "Economy", "Successfully rewarded &b" + target.getName() + " &awith &b$" + amount);
                Chat.sendMessage(target, "Economy", "&b$" + amount + " &ahas been added to your account");
            }

        }

        return true;
    }

}
