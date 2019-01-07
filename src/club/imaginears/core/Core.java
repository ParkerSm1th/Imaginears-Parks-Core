package club.imaginears.core;

import club.imaginears.core.utils.Console;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {


    private static Core instance;

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
    }

    public void registerEvents() {
        Console.Log("Loading events..", Console.types.LOG);
        PluginManager pm = Bukkit.getPluginManager();
    }
}
