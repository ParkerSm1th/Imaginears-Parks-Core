package club.imaginears.core.utils;

import club.imaginears.core.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Console {

    static ConsoleCommandSender console = Core.getInstance().getServer().getConsoleSender();

    public enum types {
        LOG, SUCCESS, WARNING, ERROR, CRITICAL, DEBUG;
    }

    public static void Log(String message, types type) {
        if (type == types.LOG) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &f" + message));
            return;
        }
        if (type == types.DEBUG) {
            if (Core.debug) {
                console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &f" + message));
                return;
            }
        }
        if (type == types.SUCCESS) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &a" + message));
            return;
        }
        if (type == types.WARNING) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &e" + message));
            return;
        }
        if (type == types.ERROR) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &c" + message));
            return;
        }
        if (type == types.CRITICAL) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &4" + message));
            return;
        }


    }


}
