package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.FileAccess;
import club.imaginears.core.utils.InventoryManager;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FixInventory implements CommandExecutor {

    public static FileConfiguration inventories = FileAccess.getInventories();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.fixinventory")) {
            if (args.length > 2 || args.length < 2) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/fixinventory (Player) (play/build)");
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/fixinventory (Online Player) (play/build)");
                }
                String type = args[1];
                switch(type) {
                    case "play":
                        inventories.set(target.getUniqueId() + ".play", null);
                        InventoryManager.saveFile();
                        if (!Build.buildMode.contains(target.getName())) {
                            Build.buildMode.add(target.getName());
                            InventoryManager.loadBuildInventory(target);
                        }
                        ReloadInventories.reloadInventories();
                        Chat.sendMessage(p, "Staff", "Fixed &b" + p.getName() + "&a's play inventory");
                        Chat.sendMessage(target, "Staff", "Your play inventory has been reset, You may now use /build to go back to your play inventory");
                        break;
                    case "build":
                        inventories.set(target.getUniqueId() + ".build", null);
                        InventoryManager.saveFile();
                        if (Build.buildMode.contains(target.getName())) {
                            Build.buildMode.remove(target.getName());
                            InventoryManager.loadPlayInventory(target);
                        }
                        ReloadInventories.reloadInventories();
                        Chat.sendMessage(p, "Staff", "Fixed &b" + p.getName() + "&a's build inventory");
                        Chat.sendMessage(target, "Staff", "Your build inventory has been reset, You may now use /build to go back to your build inventory");
                        break;
                    default:
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/fixinventory (Player) (play/build)");
                        break;
                }
            }

        }

        return true;
    }

}
