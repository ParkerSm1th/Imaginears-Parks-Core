package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPCoord implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.tpcoord")) {
            if (args.length > 3 || args.length < 3) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/tpcoord (x) (y) (z)");
            }
            if (args.length == 3) {
                Double x = Double.parseDouble(args[0]);
                Double y = Double.parseDouble(args[1]);
                Double z = Double.parseDouble(args[2]);
                p.teleport(new Location(p.getWorld(), x, y, z));
                Chat.sendMessage(p, "Staff", "You've arrived");
            }

        }

        return true;
    }

}
