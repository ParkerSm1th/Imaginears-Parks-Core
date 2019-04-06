package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanished implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.vanish")) {
            if (Vanish.vanished.isEmpty()) {
                Chat.sendMessage(p, "Staff", "&aCurrently no one is vanished");
                return true;
            }
            Chat.sendMessage(p, "Staff", "&aCurrently Vanished: &r&b" + String.join("&a, &b", Vanish.vanished));
        }

        return true;
    }

}
