package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.kick")) {
            if (args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/kick (Player) [Reason]");
            }
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/kick (Online Player) [Reason]");
                    return true;
                }
                User targetuser = Core.getUser(target.getUniqueId());
                String reason;
                if (args[1] != null) {
                    StringBuilder builder = new StringBuilder();
                    int startArg = 1;
                    int endArg = args.length;
                    for(int i = startArg; i< endArg; i++){
                        builder.append(args[i] + (args.length > (i+1) ? " " : ""));
                    }

                    reason = builder.toString();
                } else {
                    reason = "Violating the Imaginears Club rules";
                }
                club.imaginears.core.objects.Kick kick = new club.imaginears.core.objects.Kick(targetuser, reason, u);
                kick.init();

            }

        }

        return true;
    }

}
