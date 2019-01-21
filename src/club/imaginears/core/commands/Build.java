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

import java.util.ArrayList;

public class Build implements CommandExecutor {

    public static ArrayList<String> buildMode = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.buildmode")) {
            if (args.length == 0) {
                if (buildMode.contains(p.getName())) {
                    buildMode.remove(p.getName());
                    InventoryManager.saveBuildInventory(p);
                    InventoryManager.loadPlayInventory(p);
                    p.setGameMode(GameMode.ADVENTURE);
                    Chat.sendMessage(p, "Staff", "You are no longer in build mode");
                } else {
                    buildMode.add(p.getName());
                    InventoryManager.savePlayInventory(p);
                    InventoryManager.loadBuildInventory(p);
                    p.setGameMode(GameMode.CREATIVE);
                    Chat.sendMessage(p, "Staff", "You are now in build mode");
                }
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/build");
            }

        }

        return true;
    }

}
