package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ReloadInventories implements CommandExecutor {

    public static void reloadInventories() {
        try {
            Core.getInstance().inventories.load(Core.getInstance().inventoriesFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.reloadinventories")) {
            if (args.length == 0) {
                try {
                    Core.getInstance().inventories.load(Core.getInstance().inventoriesFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Chat.sendMessage(p, "Staff", "Reloaded the inventories file");
            }
            if (args.length > 0 || args.length < 0) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/reloadinventories");
            }

        }

        return true;
    }

}
