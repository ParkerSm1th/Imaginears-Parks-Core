package club.imaginears.core.utils;

import club.imaginears.core.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;


public class FileAccess {

    public static FileConfiguration getWarps() {
        return Core.getInstance().warps;
    }

    public static FileConfiguration getInventories() {
        return Core.getInstance().inventories;
    }

    public static boolean isSet(FileConfiguration file, String path) {
        return file.isSet(path);
    }

    public static String getString(FileConfiguration file, String path) {
        return file.getString(path);
    }

    public static void setString(FileConfiguration file, String path, String set) {
        file.set(path, set);
        try {
            Core.getInstance().warps.save(Core.getInstance().warpsFile);
            Console.Log("saved warps file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setInt(FileConfiguration file, String path, Integer set) {
        file.set(path, set);
        try {
            Core.getInstance().warps.save(Core.getInstance().warpsFile);
            Console.Log("saved warps file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDouble(FileConfiguration file, String path, Double set) {
        file.set(path, set);
        try {
            Core.getInstance().warps.save(Core.getInstance().warpsFile);
            Console.Log("saved warps file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFloat(FileConfiguration file, String path, Float set) {
        file.set(path, set);
        try {
            Core.getInstance().warps.save(Core.getInstance().warpsFile);
            Console.Log("saved warps file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
