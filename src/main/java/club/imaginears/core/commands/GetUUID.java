package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetUUID implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.getuuid")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/getuuid (Player)");
            }
            if (args.length == 1) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                Chat.sendMessage(p, "Staff", "UUID: &b" + target.getUniqueId().toString());
            }

        }

        return true;
    }

}
