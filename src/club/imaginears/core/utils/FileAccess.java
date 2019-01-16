package club.imaginears.core.utils;

import club.imaginears.core.Core;
import org.bukkit.configuration.file.FileConfiguration;


public class FileAccess {

    public static FileConfiguration getWarps() {
        return Core.getInstance().warps;
    }

    public static boolean isSet(FileConfiguration file, String path) {
        return file.isSet(path);
    }

    public static String getString(FileConfiguration file, String path) {
        return file.getString(path);
    }

    public static void set(FileConfiguration file, String path, String set) {
        file.set(path, set);
    }

}
