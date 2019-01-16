package club.imaginears.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class Warps {

    public static FileConfiguration warpsfile = FileAccess.getWarps();

    public static boolean checkWarp(String name) {

        if (FileAccess.isSet(warpsfile, "warps." + name)) {
            return true;
        } else {
            return false;
        }

    }

    public static Location getWarp(String name) {
        Double x = Double.parseDouble(FileAccess.getString(warpsfile, "warps." + name + ".x"));
        Double y = Double.parseDouble(FileAccess.getString(warpsfile, "warps." + name + ".y"));
        Double z = Double.parseDouble(FileAccess.getString(warpsfile, "warps." + name + ".z"));
        Float yaw = Float.parseFloat(FileAccess.getString(warpsfile, "warps." + name + ".yaw"));
        Float pitch = Float.parseFloat(FileAccess.getString(warpsfile, "warps." + name + ".pitch"));

        Location warp = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);

        return warp;
    }

    public static String getWarpType(String name) {
        Integer type = Integer.parseInt(FileAccess.getString(warpsfile, "warps." + name + ".type"));
        switch(type) {
            case 0:
                return "ride";
            case 1:
                return "show";
            case 2:
                return "shop";
            case 3:
                return "events";
            case 4:
                return "park";
        }
        return null;
    }

    public static void setWarp(String name, String type, Location loc) {

        String start = "warps." + name + ".";

        warpsfile.set(start + "x", loc.getX());
        warpsfile.set(start + "y", loc.getY());
        warpsfile.set(start + "z", loc.getZ());
        warpsfile.set(start + "yaw", loc.getYaw());
        warpsfile.set(start + "pitch", loc.getPitch());
        warpsfile.set(start + "type", type);

    }

}
