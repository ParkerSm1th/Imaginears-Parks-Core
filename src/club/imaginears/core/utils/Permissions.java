package club.imaginears.core.utils;

import me.lucko.luckperms.LuckPerms;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Permissions {

    public static boolean checkPermission(Player p, String perm) {
        if (p.hasPermission(perm) || p.hasPermission("core.all")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPermissionMsg(Player p, String perm) {
        if (p.hasPermission(perm) || p.hasPermission("core.all")) {
            return true;
        } else {
            Chat.sendError(p, Chat.ChatErrors.PERMS, null);
            return false;
        }
    }

    public static String getRankPrefix(Player p) {
        String group = LuckPerms.getApi().getUser(p.getUniqueId()).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return "&f[&eGuest&f]";
        } else if (group.equalsIgnoreCase("silver")) {
            return "&f[&6Silver&f]";
        } else if (group.equalsIgnoreCase("gold")) {
            return "&f[&6Gold&f]";
        } else if (group.equalsIgnoreCase("platinum")) {
            return "&f[&6Platinum&f]";
        } else if (group.equalsIgnoreCase("platinum+")) {
            return "&f[&6Platinum+&f]";
        } else if (group.equalsIgnoreCase("specialguest")) {
            return "&0[&4Special Guest&0]";
        } else if (group.equalsIgnoreCase("security")) {
            return "&f[&4Security&f]";
        } else if (group.equalsIgnoreCase("photopass")) {
            return "&f[&3Photopass&f]";
        } else if (group.equalsIgnoreCase("character")) {
            return "&f[&6Character&f]";
        } else if (group.equalsIgnoreCase("castmember")) {
            return "&f[&3Cast Member&f]";
        } else if (group.equalsIgnoreCase("coordinator")) {
            return "&f[&cCoordinator&f]";
        } else if (group.equalsIgnoreCase("manager")) {
            return "&f[&aManager&f]";
        } else if (group.equalsIgnoreCase("developer")) {
            return "&f[&5Developer&f]";
        } else {
            return "&f[&eGuest&f]";
        }
    }

    public static String getOfflineRankPrefix(OfflinePlayer p) {
        String group = LuckPerms.getApi().getUser(p.getUniqueId()).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return "&f[&eGuest&f]";
        } else if (group.equalsIgnoreCase("silver")) {
            return "&f[&6Silver&f]";
        } else if (group.equalsIgnoreCase("gold")) {
            return "&f[&6Gold&f]";
        } else if (group.equalsIgnoreCase("platinum")) {
            return "&f[&6Platinum&f]";
        } else if (group.equalsIgnoreCase("platinum+")) {
            return "&f[&6Platinum+&f]";
        } else if (group.equalsIgnoreCase("specialguest")) {
            return "&0[&4Special Guest&0]";
        } else if (group.equalsIgnoreCase("security")) {
            return "&f[&4Security&f]";
        } else if (group.equalsIgnoreCase("photopass")) {
            return "&f[&3Photopass&f]";
        } else if (group.equalsIgnoreCase("character")) {
            return "&f[&6Character&f]";
        } else if (group.equalsIgnoreCase("castmember")) {
            return "&f[&3Cast Member&f]";
        } else if (group.equalsIgnoreCase("coordinator")) {
            return "&f[&cCoordinator&f]";
        } else if (group.equalsIgnoreCase("manager")) {
            return "&f[&aManager&f]";
        } else if (group.equalsIgnoreCase("developer")) {
            return "&f[&5Developer&f]";
        } else {
            return "&f[&eGuest&f]";
        }
    }

    public static String getTextRankPrefix(String s) {
        String group = s;
        if (group.equalsIgnoreCase("default")) {
            return "&f[&eGuest&f]";
        } else if (group.equalsIgnoreCase("silver")) {
            return "&f[&6Silver&f]";
        } else if (group.equalsIgnoreCase("gold")) {
            return "&f[&6Gold&f]";
        } else if (group.equalsIgnoreCase("platinum")) {
            return "&f[&6Platinum&f]";
        } else if (group.equalsIgnoreCase("platinum+")) {
            return "&f[&6Platinum+&f]";
        } else if (group.equalsIgnoreCase("specialguest")) {
            return "&0[&4Special Guest&0]";
        } else if (group.equalsIgnoreCase("security")) {
            return "&f[&4Security&f]";
        } else if (group.equalsIgnoreCase("photopass")) {
            return "&f[&3Photopass&f]";
        } else if (group.equalsIgnoreCase("character")) {
            return "&f[&6Character&f]";
        } else if (group.equalsIgnoreCase("castmember")) {
            return "&f[&3Cast Member&f]";
        } else if (group.equalsIgnoreCase("coordinator")) {
            return "&f[&cCoordinator&f]";
        } else if (group.equalsIgnoreCase("manager")) {
            return "&f[&aManager&f]";
        } else if (group.equalsIgnoreCase("developer")) {
            return "&f[&5Developer&f]";
        } else {
            return "&f[&eGuest&f]";
        }
    }

    public static String getRankColor(Player p) {
        String group = LuckPerms.getApi().getUser(p.getUniqueId()).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return "&e";
        } else if (group.equalsIgnoreCase("silver")) {
            return "&6";
        } else if (group.equalsIgnoreCase("gold")) {
            return "&6";
        } else if (group.equalsIgnoreCase("platinum")) {
            return "&6";
        } else if (group.equalsIgnoreCase("platinum+")) {
            return "&6";
        } else if (group.equalsIgnoreCase("specialguest")) {
            return "&4";
        } else if (group.equalsIgnoreCase("security")) {
            return "&4";
        } else if (group.equalsIgnoreCase("photopass")) {
            return "&3";
        } else if (group.equalsIgnoreCase("character")) {
            return "&6";
        } else if (group.equalsIgnoreCase("castmember")) {
            return "&3";
        } else if (group.equalsIgnoreCase("coordinator")) {
            return "&c";
        } else if (group.equalsIgnoreCase("manager")) {
            return "&a";
        } else if (group.equalsIgnoreCase("developer")) {
            return "&5";
        } else {
            return "&e";
        }

    }

    public static String getChatColor(Player p) {
        String group = LuckPerms.getApi().getUser(p.getUniqueId()).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return "&f";
        } else if (group.equalsIgnoreCase("silver")) {
            return "&f";
        } else if (group.equalsIgnoreCase("gold")) {
            return "&f";
        } else if (group.equalsIgnoreCase("platinum")) {
            return "&f";
        } else if (group.equalsIgnoreCase("platinum+")) {
            return "&f";
        } else if (group.equalsIgnoreCase("specialguest")) {
            return "&f";
        } else if (group.equalsIgnoreCase("security")) {
            return "&f";
        } else if (group.equalsIgnoreCase("photopass")) {
            return "&f";
        } else if (group.equalsIgnoreCase("character")) {
            return "&6";
        } else if (group.equalsIgnoreCase("castmember")) {
            return "&f";
        } else if (group.equalsIgnoreCase("coordinator")) {
            return "&f";
        } else if (group.equalsIgnoreCase("manager")) {
            return "&f";
        } else if (group.equalsIgnoreCase("developer")) {
            return "&f";
        } else {
            return "&f";
        }

    }

}
