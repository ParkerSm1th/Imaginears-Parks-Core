package club.imaginears.core.commands;

import club.imaginears.core.objects.Rank;
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

    public static void enableBuildMode(Player p) {
        if (p.getInventory().getItem(1) != null) {
            if (p.getInventory().getItem(1).getItemMeta().getDisplayName().contains("Magic")) {
                InventoryManager.savePlayInventory(p);
                p.setGameMode(GameMode.CREATIVE);
                InventoryManager.loadBuildInventory(p);
            } else {
                p.setGameMode(GameMode.CREATIVE);
                InventoryManager.loadBuildInventory(p);
            }
        } else {
            p.setGameMode(GameMode.CREATIVE);
            InventoryManager.loadBuildInventory(p);
        }

        Chat.sendMessage(p, "Staff", "You are now in build mode");
        buildMode.add(p.getName());
    }

    public static void disableBuildMode(Player p) {
        InventoryManager.saveBuildInventory(p);
        p.setGameMode(GameMode.ADVENTURE);
        InventoryManager.loadPlayInventory(p);

        Chat.sendMessage(p, "Staff", "You are no longer in build mode");
        buildMode.remove(p.getName());
    }

    public static boolean checkBuildMode(Player p) {
        if (buildMode.contains(p.getName())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.buildmode")) {
            if (args.length == 0) {
                if (checkBuildMode(p)) {
                    disableBuildMode(p);
                } else {
                    enableBuildMode(p);
                }
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/build");
            }

        }



        return true;
    }

}
