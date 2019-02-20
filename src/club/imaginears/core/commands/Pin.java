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

public class Pin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.pin")) {
            if (args.length == 0) {
                String pin = u.getPin();
                Chat.sendMessage(p, "Staff", "Your staff panel pin is: &b" + pin + "&a, this pin will expire in 3 minutes.");
                Chat.sendMessage(p, "Staff", "You can also just click this link to login to the staff panel: &bhttps://imaginears.club/hub/index.php?quicklogin=1&username=" + p.getName() + "&pin=" + pin);
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/pin");
            }

        }

        return true;
    }

}
