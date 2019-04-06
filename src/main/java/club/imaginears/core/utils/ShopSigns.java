package club.imaginears.core.utils;

import club.imaginears.core.Core;
import club.imaginears.core.objects.Shop;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static club.imaginears.core.utils.Console.types.LOG;

public class ShopSigns {

    public static FileConfiguration shops = FileAccess.getShops();
    public static File shopsFile = Core.getInstance().shopsFile;

    public static String getStringLoc(Location loc) {
        return loc.toString();
    }
    
    public static void saveFile() {
        try {
            Core.getInstance().shops.save(Core.getInstance().shopsFile);
            Console.Log("saved shops file", Console.types.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkShop(String uuid) {
        String shopid = uuid;
        if (shops.isSet(shopid)) {
            return true;
        } else {
            return false;
        }

    }

    public static ItemStack[] getShopItems(String uuid) {
        ConfigurationSection shop = shops.getConfigurationSection(uuid);
        try {
            ItemStack[] stuff = InventoryStringDeSerializer.itemStackArrayFromBase64(shop.getString("items"));
            return stuff;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Float getPrice(String uuid) {
        ConfigurationSection shop = shops.getConfigurationSection(uuid);
        Float price = Float.parseFloat(shop.getString("price"));
        return price;
    }

    public static String getName(String uuid) {
        ConfigurationSection shop = shops.getConfigurationSection(uuid);
        String name = shop.getString("name");
        return name;
    }

    public static String getStore(String uuid) {
        ConfigurationSection shop = shops.getConfigurationSection(uuid);
        String store = shop.getString("store");
        return store;
    }

    public static Location getLocation(String uuid) {
        ConfigurationSection shop = shops.getConfigurationSection(uuid);
        Location location = new Location(Bukkit.getWorld(shop.getString("location.world")),  Double.parseDouble(shop.getString("location.x")), Double.parseDouble(shop.getString("location.y")),  Double.parseDouble(shop.getString("location.z")));
        return location;
    }

    public static Shop getShop(String uuid) {
        return new Shop(getLocation(uuid), getName(uuid), getStore(uuid), getPrice(uuid), getShopItems(uuid), uuid);
    }

    public static void setupShop(String name, String store, Float price, Location location, ItemStack[] items) {
        String prefix = UUID.randomUUID().toString();
        shops.set(prefix + ".name", name);
        shops.set(prefix + ".store", store);
        shops.set(prefix + ".price", price);
        shops.set(prefix + ".location.world", location.getWorld().getName());
        shops.set(prefix + ".location.x", location.getX());
        shops.set(prefix + ".location.y", location.getY());
        shops.set(prefix + ".location.z", location.getZ());
        shops.set(prefix + ".location.yaw", location.getY());
        shops.set(prefix + ".location.pitch", location.getPitch());
        shops.set(prefix + ".items", InventoryStringDeSerializer.itemStackArrayToBase64(items));
        saveFile();
        Core.enabledShops.put(location, getShop(prefix));
    }

    public static void loadShops() {
        for (String s : shops.getKeys(false)) {
            ConfigurationSection shop = shops.getConfigurationSection(s);
            Location location = new Location(Bukkit.getWorld(shop.getString("location.world")),  Double.parseDouble(shop.getString("location.x")), Double.parseDouble(shop.getString("location.y")),  Double.parseDouble(shop.getString("location.z")));
            Core.enabledShops.put(location, getShop(s));
        }
    }

    public static void delShop(String uuid) {
        shops.set(uuid, null);
        saveFile();
    }

}
