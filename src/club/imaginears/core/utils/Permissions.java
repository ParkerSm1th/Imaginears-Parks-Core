package club.imaginears.core.utils;

import club.imaginears.core.objects.Rank;
import me.lucko.luckperms.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

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

    public static Rank getRankFromLadder(Integer rank) {
        switch(rank) {
            case 0:
                return Rank.GUEST;
            case 1:
                return Rank.SILVER;
            case 2:
                return Rank.GOLD;
            case 3:
                return Rank.PLATINUM;
            case 4:
                return Rank.PLATINUMPLUS;
            case 5:
                return Rank.SPECIALGUEST;
            case 6:
                return Rank.SECURITY;
            case 7:
                return Rank.PHOTOPASS;
            case 8:
                return Rank.CHARACTER;
            case 9:
                return Rank.CASTMEMBER;
            case 10:
                return Rank.COORDINATOR;
            case 11:
                return Rank.MANAGER;
            case 12:
                return Rank.DEVELOPER;
            default:
                return Rank.GUEST;
        }
    }

    public static Rank getRank(Player p) {
        String group = LuckPerms.getApi().getUser(p.getUniqueId()).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return Rank.GUEST;
        } else if (group.equalsIgnoreCase("silver")) {
            return Rank.SILVER;
        } else if (group.equalsIgnoreCase("gold")) {
            return Rank.GOLD;
        } else if (group.equalsIgnoreCase("platinum")) {
            return Rank.PLATINUM;
        } else if (group.equalsIgnoreCase("platinum+")) {
            return Rank.PLATINUMPLUS;
        } else if (group.equalsIgnoreCase("specialguest")) {
            return Rank.SPECIALGUEST;
        } else if (group.equalsIgnoreCase("security")) {
            return Rank.SECURITY;
        } else if (group.equalsIgnoreCase("photopass")) {
            return Rank.PHOTOPASS;
        } else if (group.equalsIgnoreCase("character")) {
            return Rank.CHARACTER;
        } else if (group.equalsIgnoreCase("castmember")) {
            return Rank.CASTMEMBER;
        } else if (group.equalsIgnoreCase("coordinator")) {
            return Rank.COORDINATOR;
        } else if (group.equalsIgnoreCase("manager")) {
            return Rank.MANAGER;
        } else if (group.equalsIgnoreCase("developer")) {
            return Rank.DEVELOPER;
        } else {
            return Rank.GUEST;
        }
    }

    public static Rank getRankUUID(String uuid) {
        String group = LuckPerms.getApi().getUser(UUID.fromString(uuid)).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return Rank.GUEST;
        } else if (group.equalsIgnoreCase("silver")) {
            return Rank.SILVER;
        } else if (group.equalsIgnoreCase("gold")) {
            return Rank.GOLD;
        } else if (group.equalsIgnoreCase("platinum")) {
            return Rank.PLATINUM;
        } else if (group.equalsIgnoreCase("platinum+")) {
            return Rank.PLATINUMPLUS;
        } else if (group.equalsIgnoreCase("specialguest")) {
            return Rank.SPECIALGUEST;
        } else if (group.equalsIgnoreCase("security")) {
            return Rank.SECURITY;
        } else if (group.equalsIgnoreCase("photopass")) {
            return Rank.PHOTOPASS;
        } else if (group.equalsIgnoreCase("character")) {
            return Rank.CHARACTER;
        } else if (group.equalsIgnoreCase("castmember")) {
            return Rank.CASTMEMBER;
        } else if (group.equalsIgnoreCase("coordinator")) {
            return Rank.COORDINATOR;
        } else if (group.equalsIgnoreCase("manager")) {
            return Rank.MANAGER;
        } else if (group.equalsIgnoreCase("developer")) {
            return Rank.DEVELOPER;
        } else {
            return Rank.GUEST;
        }
    }

    public static Rank getOfflineRank(OfflinePlayer p) {
        String group = LuckPerms.getApi().getUser(p.getUniqueId()).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return Rank.GUEST;
        } else if (group.equalsIgnoreCase("silver")) {
            return Rank.SILVER;
        } else if (group.equalsIgnoreCase("gold")) {
            return Rank.GOLD;
        } else if (group.equalsIgnoreCase("platinum")) {
            return Rank.PLATINUM;
        } else if (group.equalsIgnoreCase("platinum+")) {
            return Rank.PLATINUMPLUS;
        } else if (group.equalsIgnoreCase("specialguest")) {
            return Rank.SPECIALGUEST;
        } else if (group.equalsIgnoreCase("security")) {
            return Rank.SECURITY;
        } else if (group.equalsIgnoreCase("photopass")) {
            return Rank.PHOTOPASS;
        } else if (group.equalsIgnoreCase("character")) {
            return Rank.CHARACTER;
        } else if (group.equalsIgnoreCase("castmember")) {
            return Rank.CASTMEMBER;
        } else if (group.equalsIgnoreCase("coordinator")) {
            return Rank.COORDINATOR;
        } else if (group.equalsIgnoreCase("manager")) {
            return Rank.MANAGER;
        } else if (group.equalsIgnoreCase("developer")) {
            return Rank.DEVELOPER;
        } else {
            return Rank.GUEST;
        }
    }


}
