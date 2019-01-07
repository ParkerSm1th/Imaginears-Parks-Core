package club.imaginears.core.utils;

import club.imaginears.core.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Console {

    static ConsoleCommandSender console = Core.getInstance().getServer().getConsoleSender();

    public enum types {
        LOG, SUCCESS, WARNING, ERROR, CRITICAL;
    }

    public static void Log(String message, types type) {
        if (type == types.LOG) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &f"));
            return;
        }
        if (type == types.SUCCESS) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &a"));
            return;
        }
        if (type == types.WARNING) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &e"));
            return;
        }
        if (type == types.ERROR) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &c"));
            return;
        }
        if (type == types.CRITICAL) {
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &4"));
            return;
        }


    }


}
