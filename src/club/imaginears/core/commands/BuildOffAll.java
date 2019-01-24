package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.InventoryManager;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildOffAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.buildoffall")) {
            if (args.length == 0) {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    if (Build.checkBuildMode(pl)) {
                        Chat.sendMessage(pl, "Staff", "The plugin is being reloaded, you will be taken out of build mode");
                        Build.disableBuildMode(pl);
                    } else {
                        InventoryManager.savePlayInventory(pl);
                    }
                }
                Chat.sendMessage(p, "Developer", "Disabled build mode for everyone");
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/buildoffall");
            }

        }

        return true;
    }

}
