package club.imaginears.core.utils;

import club.imaginears.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Warps {

    public static FileConfiguration warpsfile = FileAccess.getWarps();

    public static void saveFile() {
        try {
            Core.getInstance().warps.save(Core.getInstance().warpsFile);
            Console.Log("saved warps file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkWarp(String name) {

        if (warpsfile.isSet(name)) {
            return true;
        } else {
            return false;
        }

    }

    public static Location getWarp(String name) {
        ConfigurationSection warp = warpsfile.getConfigurationSection(name);
        Double x = Double.parseDouble(warp.get("x").toString());
        Double y = Double.parseDouble(warp.get("y").toString());
        Double z = Double.parseDouble(warp.get("z").toString());
        Float yaw = Float.parseFloat(warp.get("yaw").toString());
        Float pitch = Float.parseFloat(warp.get("pitch").toString());

        Location warploc = new Location(Bukkit.getWorld("mk"), x, y, z, yaw, pitch);

        return warploc;
    }

    public static String getWarpPerm(String name) {
        ConfigurationSection warp = warpsfile.getConfigurationSection(name);
        String perm = warp.getString("perm");
        return perm;
    }

    public static String getWarpType(String name) {
        ConfigurationSection warp = warpsfile.getConfigurationSection(name);
        Integer type = warp.getInt("type");
        switch(type) {
            case 0:
                return "ride";
            case 1:
                return "show";
            case 2:
                return "shop";
            case 3:
                return "event";
            case 4:
                return "park";
            case 5:
                return "location";
        }
        return null;
    }

    public static List<String> getWarps(Integer type) {
        List<String> warps = new ArrayList<String>();

        for (String s : warpsfile.getKeys(false)) {
            ConfigurationSection warp = warpsfile.getConfigurationSection(s);
            if (warp.getInt("type") == type) {
                warps.add(s);
            }
        }
        return warps;
    }

    public static void setWarp(String name, String type, String perm, Location loc) {


        warpsfile.createSection(name);
        ConfigurationSection newWarp = warpsfile.getConfigurationSection(name);
        newWarp.set("x", loc.getX());
        newWarp.set("y", loc.getY());
        newWarp.set("z", loc.getZ());
        newWarp.set("yaw", loc.getYaw());
        newWarp.set("pitch", loc.getPitch());
        newWarp.set("perm", perm);
        switch(type) {
            case "ride":
                newWarp.set("type", 0);
                break;
            case "show":
                newWarp.set("type", 1);
                break;
            case "shop":
                newWarp.set("type", 2);
                break;
            case "event":
                newWarp.set("type", 3);
                break;
            case "park":
                newWarp.set("type", 4);
                break;
            case "location":
                newWarp.set("type", 5);
                break;
        }
        saveFile();

    }

    public static void delWarp(String name) {
        warpsfile.set(name, null);
        saveFile();
    }

}
