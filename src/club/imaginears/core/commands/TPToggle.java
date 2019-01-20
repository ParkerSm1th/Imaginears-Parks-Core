package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TPToggle implements CommandExecutor {

    public static ArrayList<String> tpoff = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.tptoggle")) {
            if (args.length == 0) {
                if (tpoff.contains(p.getName())) {
                    tpoff.remove(p.getName());
                    Chat.sendMessage(p, "Staff", "People can now meet up with you");
                } else {
                    tpoff.add(p.getName());
                    Chat.sendMessage(p, "Staff", "People can no longer meet up with you");
                }
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/tptoggle");
            }

        }

        return true;
    }

}
