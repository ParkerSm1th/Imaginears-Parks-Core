package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.GUIs;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Profile implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.profile")) {
            if (args.length > 1 || args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/profile (User)");
            }
            if (args.length == 1) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                if (!target.hasPlayedBefore()) {
                    Chat.sendError(p, Chat.ChatErrors.COMMON, "That player has never joined Imaginears!");
                    return true;
                }
                GUIs.openProfile(p, target);
            }

        }

        return true;
    }

}
