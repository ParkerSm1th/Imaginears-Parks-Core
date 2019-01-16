package club.imaginears.core;

import club.imaginears.core.commands.*;
import club.imaginears.core.events.AsyncPlayerChat;
import club.imaginears.core.events.PlayerCommandPreprocess;
import club.imaginears.core.events.PlayerJoin;
import club.imaginears.core.events.PlayerLeave;
import club.imaginears.core.utils.Console;
import club.imaginears.core.utils.GUIs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {


    private static Core instance;
    public static Boolean debug = true;

    private static club.imaginears.core.Core c;

    @Override
    public void onEnable() {

        instance = this;
        Console.Log("Starting core..", Console.types.LOG);
        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        instance = null;
        Console.Log("Stopping core..", Console.types.LOG);
    }

    public static Core getInstance() {
        return instance;
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
        Console.Log("Loaded events..", Console.types.LOG);
    }
}
