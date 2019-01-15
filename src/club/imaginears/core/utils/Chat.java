package club.imaginears.core.utils;

import club.imaginears.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

    public enum ChatErrors {
        PERMS, ARGCOUNT, INVALIDARG;
    }

    public static String sendColorFree(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendMessage(Player player, String topic, String msg) {
        player.sendMessage(sendColorFree("&b" + topic + "&7 » &a" + msg));
    }


    public static void sendError(Player player, ChatErrors error, String extra) {
        if (error == ChatErrors.PERMS) {
            player.sendMessage(sendColorFree("&bPermissions" + "&7 » &cYou do not have access to that command."));
        } else if (error == ChatErrors.ARGCOUNT) {
            player.sendMessage(sendColorFree("&bCommand" + "&7 » &cInvalid usage, please use: " + extra));
        } else if (error == ChatErrors.INVALIDARG) {
            player.sendMessage(sendColorFree("&bCommand" + "&7 » &cInvalid arguments, please use: " + extra));
        }
    }

    public static void permsError(Player player) {
        player.sendMessage(sendColorFree("&bPermissions" + "&7 » &cYou do not have access to that command."));
    }

    public static void broadcastMessage(String topic, String msg) {
        Core.getInstance().getServer().broadcastMessage(sendColorFree("&b" + topic + "&7 » &f" + msg));
    }

}
