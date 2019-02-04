package club.imaginears.core.utils;

import club.imaginears.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WhitelistManager {
    public static FileConfiguration whitelist = FileAccess.getWhitelist();

    public static void saveFile() {
        try {
            Core.getInstance().whitelist.save(Core.getInstance().whitelistFile);
            Console.Log("saved whitelist file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void on() {
        whitelist.set("enabled", "on");
        saveFile();
    }

    public static void off() {
        whitelist.set("enabled", "off");
        saveFile();
    }

    public static boolean checkStatus() {
        if (whitelist.getString("enabled").equalsIgnoreCase("on")) {
            return true;
        } else {
            return false;
        }
    }

    public static void enableRanks() {
        whitelist.set("ranks", "on");
        saveFile();
    }

    public static void disableRanks() {
        whitelist.set("ranks", "off");
        saveFile();
    }

    public static void setRank(Integer rank) {
        whitelist.set("min-rank", rank);
        saveFile();
    }

    public static Integer getRank() {
        return Integer.parseInt(whitelist.getString("min-rank"));
    }

    public static boolean checkRanks() {
        if (whitelist.getString("ranks").equalsIgnoreCase("on")) {
            return true;
        } else {
            return false;
        }
    }

    public static void enablePlayers() {
        whitelist.set("players", "on");
        saveFile();
    }

    public static void disablePlayers() {
        whitelist.set("players", "off");
        saveFile();
    }

    public static boolean checkPlayers() {
        if (whitelist.getString("players").equalsIgnoreCase("on")) {
            return true;
        } else {
            return false;
        }
    }

    public static List<String> getPlayers() {
        return whitelist.getStringList("whitelist");
    }

    public static void addPlayer(String uuid) {
        List<String> pls = whitelist.getStringList("whitelist");
        Console.Log(whitelist.getStringList("whitelist").toString(), Console.types.DEBUG);
        pls.add(uuid);
        whitelist.set("whitelist", pls);
        saveFile();
    }

    public static void removePlayer(String uuid) {
        List<String> pls = whitelist.getStringList("whitelist");
        pls.remove(uuid);
        whitelist.set("whitelist", pls);
        saveFile();
    }

    public static boolean checkPlayerList(String uuid) {
        if (whitelist.getStringList("whitelist").contains(uuid)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPlayer(String uuid) {
        if (checkPlayers()) {
            if (whitelist.getStringList("whitelist").contains(uuid)) {
                return true;
            } else {
                if (checkRanks()) {
                    if (Permissions.getRankUUID(uuid).rankLadder >= getRank()) {
                        return true;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else if (checkRanks()) {
            if (Permissions.getRankUUID(uuid).rankLadder >= getRank()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
