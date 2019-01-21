package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.speed")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/speed (1-5)");
            }
            if (args.length == 1) {
                Float speed = Float.parseFloat(args[0]);
                if (speed < 1 || speed > 5) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/speed (1-5)");
                    return true;
                }
                if (p.isFlying()) {
                    p.setFlySpeed(speed / 10);
                    Chat.sendMessage(p, "Staff", "Set Peter Pan speed to &b" + args[0]);
                } else {
                    p.setWalkSpeed(speed / 10);
                    Chat.sendMessage(p, "Staff", "Set walking speed to &b" + args[0]);
                }
            }

        }

        return true;
    }

}
