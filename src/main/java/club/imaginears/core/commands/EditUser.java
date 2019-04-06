package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.GUIs;
import club.imaginears.core.utils.Permissions;
import me.lucko.luckperms.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditUser implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.edituser")) {

            if (args.length != 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/edituser (Player)");
                return true;
            }

            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

            if (target != null) {
                GUIs.openEditUserGUI(p, target);
            } else {
                Chat.sendMessage(p, "User", "That player has never logged on to Imaginears Club.");
            }

        }

        return true;
    }
}
