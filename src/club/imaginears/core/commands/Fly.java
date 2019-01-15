package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.fly")) {
            if (args.length == 0) {
                if (p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    Chat.sendMessage(p, "Staff", "Peter Pan mode disabled");
                } else {
                    p.setAllowFlight(true);
                    Chat.sendMessage(p, "Staff", "Peter Pan mode enabled");
                }
            }
            if (args.length > 1) {
                if (Permissions.checkPermission(p, "core.flyothers")) {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/fly or /fly (Player)");
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/fly");
                }
            }
            if (args.length == 1) {
                if (Permissions.checkPermission(p, "core.flyothers")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        Chat.sendMessage(p, "Error", "That player is not online.");
                        return true;
                    }
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        Chat.sendMessage(target, "Staff", "Peter Pan mode disabled");
                        Chat.sendMessage(p, "Staff", "Peter Pan mode disabled for &b" + target.getName());
                    } else {
                        target.setAllowFlight(true);
                        Chat.sendMessage(target, "Staff", "Peter Pan mode enabled");
                        Chat.sendMessage(p, "Staff", "Peter Pan mode enabled for &b" + target.getName());
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/fly");
                }
            }
        }

        return true;
    }
}
