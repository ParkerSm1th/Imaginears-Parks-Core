package club.imaginears.core;

import club.imaginears.core.commands.*;
import club.imaginears.core.events.AsyncPlayerChat;
import club.imaginears.core.events.PlayerCommandPreprocess;
import club.imaginears.core.events.PlayerJoin;
import club.imaginears.core.events.PlayerLeave;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Console;
import club.imaginears.core.utils.GUIs;
import club.imaginears.core.utils.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Core extends JavaPlugin {


    private static Core instance;
    public static Boolean debug = true;
    public File warpsFile;
    public FileConfiguration warps;

    public File inventoriesFile;
    public FileConfiguration inventories;

    private static club.imaginears.core.Core c;

    @Override
    public void onEnable() {

        instance = this;
        Console.Log("Starting core..", Console.types.LOG);
        createFiles();
        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        instance = null;
        saveInventories();
        Console.Log("Stopping core..", Console.types.LOG);
    }

    public static Core getInstance() {
        return instance;
    }


    public void createFiles() {
        Console.Log("Loading files", Console.types.LOG);
        createWarpsFile();
        Console.Log("Loaded warps file", Console.types.LOG);
        createInventoriesFile();
        Console.Log("Loaded warps file", Console.types.LOG);
    }

    public FileConfiguration getWarpsFile() {
        return this.warps;
    }

    private void createWarpsFile() {
        warpsFile = new File(getDataFolder(), "warps.yml");
        if (!warpsFile.exists()) {
            warpsFile.getParentFile().mkdirs();
            saveResource("warps.yml", false);
        }

        warps = new YamlConfiguration();
        try {
            warps.load(warpsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getInventoriesFile() {
        return this.inventories;
    }

    public void createInventoriesFile() {
        inventoriesFile = new File(getDataFolder(), "inventories.yml");
        if (!inventoriesFile.exists()) {
            inventoriesFile.getParentFile().mkdirs();
            saveResource("inventories.yml", false);
        }

        inventories = new YamlConfiguration();
        try {
            inventories.load(inventoriesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveInventories() {
        for (Player pname : Bukkit.getOnlinePlayers()) {
            Player p = Bukkit.getPlayer(pname.getName());
            if (Build.buildMode.contains(pname.getName())) {
                Chat.sendMessage(p, "Staff", "The plugin is being reloaded, you will be taken out of build mode");
                InventoryManager.saveBuildInventory(p);
                Chat.sendMessage(p, "Staff", "Saved build");
                InventoryManager.loadPlayInventory(p);
                Chat.sendMessage(p, "Staff", "Out of build");
                Build.buildMode.remove(p.getName());
            } else {
                InventoryManager.savePlayInventory(p);
            }
        }
    }

    public void registerCommands() {
        Console.Log("Loading commands..", Console.types.LOG);
        getCommand("edituser").setExecutor(new EditUser());
        Console.Log("Loaded edituser command", Console.types.DEBUG);
        getCommand("fly").setExecutor(new Fly());
        Console.Log("Loaded fly command", Console.types.DEBUG);
        getCommand("vanish").setExecutor(new Vanish());
        Console.Log("Loaded vanish command", Console.types.DEBUG);
        getCommand("vanished").setExecutor(new Vanished());
        Console.Log("Loaded vanished command", Console.types.DEBUG);
        getCommand("nightvision").setExecutor(new Nightvision());
        Console.Log("Loaded nv command", Console.types.DEBUG);
        getCommand("teleport").setExecutor(new Teleport());
        Console.Log("Loaded teleport command", Console.types.DEBUG);
        getCommand("teleporthere").setExecutor(new TeleportHere());
        Console.Log("Loaded teleporthere command", Console.types.DEBUG);
        getCommand("setwarp").setExecutor(new SetWarp());
        Console.Log("Loaded setwarp command", Console.types.DEBUG);
        getCommand("delwarp").setExecutor(new DelWarp());
        Console.Log("Loaded delwarp command", Console.types.DEBUG);
        getCommand("warp").setExecutor(new Warp());
        Console.Log("Loaded warp command", Console.types.DEBUG);
        getCommand("warps").setExecutor(new Warps());
        Console.Log("Loaded warps command", Console.types.DEBUG);
        getCommand("tptoggle").setExecutor(new TPToggle());
        Console.Log("Loaded tptoggle command", Console.types.DEBUG);
        getCommand("flyspeed").setExecutor(new FlightSpeed());
        Console.Log("Loaded flyspeed command", Console.types.DEBUG);
        getCommand("walkspeed").setExecutor(new WalkSpeed());
        Console.Log("Loaded walkspeed command", Console.types.DEBUG);
        getCommand("build").setExecutor(new Build());
        Console.Log("Loaded build command", Console.types.DEBUG);
        getCommand("tpcoord").setExecutor(new TPCoord());
        Console.Log("Loaded tpcoord command", Console.types.DEBUG);
        getCommand("buildoffall").setExecutor(new BuildOffAll());
        Console.Log("Loaded buildoffall command", Console.types.DEBUG);
        getCommand("reloadinventories").setExecutor(new ReloadInventories());
        Console.Log("Loaded reloadinventories command", Console.types.DEBUG);
        getCommand("getuuid").setExecutor(new GetUUID());
        Console.Log("Loaded getuuid command", Console.types.DEBUG);
        getCommand("fixinventory").setExecutor(new FixInventory());
        Console.Log("Loaded fixinventory command", Console.types.DEBUG);
        Console.Log("Loaded commands..", Console.types.LOG);
    }

    public void registerEvents() {
        Console.Log("Loading events..", Console.types.LOG);
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerLeave(), this);
        pm.registerEvents(new GUIs(), this);
        pm.registerEvents(new PlayerCommandPreprocess(), this);
        pm.registerEvents(new AsyncPlayerChat(), this);
        pm.registerEvents(new InventoryManager(), this);
        Console.Log("Loaded events..", Console.types.LOG);
    }
}
