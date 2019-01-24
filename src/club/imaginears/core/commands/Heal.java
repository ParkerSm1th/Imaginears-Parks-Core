package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.heal")) {
            if (args.length == 0) {
                p.setHealth(20);
                p.setFoodLevel(20);
                Chat.sendMessage(p, "Heal", "You've been healed");
            }
            if (args.length > 0) {
                if (Permissions.checkPermission(p, "core.healothers")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/heal (Online Player)");
                    }
                    target.setHealth(20);
                    target.setFoodLevel(20);
                    Chat.sendMessage(p, "Heal", "&b" + target.getName() + " &ahas been healed");
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/heal");
                }
            }

        }

        return true;
    }

}
