package club.imaginears.core.events;

import club.imaginears.core.objects.Rank;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Console;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Warps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommandPreprocess implements Listener {

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent e) {
        String[] msg = e.getMessage().split(" ");
        Player p = e.getPlayer();

        List<String> plugins = new ArrayList();
        plugins.add("plugins");
        plugins.add("bukkit:pl");
        plugins.add("bukkit:plugins");

        List<String> plugins2 = new ArrayList();
        plugins2.add("pl");

        List<String> version = new ArrayList();
        version.add("version");
        version.add("bukkit:ver");
        version.add("bukkit:version");

        List<String> version2 = new ArrayList();
        version2.add("ver");

        List<String> about = new ArrayList();
        about.add("about");
        about.add("bukkit:about");

        List<String> question = new ArrayList();
        question.add("?");
        question.add("help");
        question.add("bukkit:?");

        List<String> inv = new ArrayList();
        inv.add("clear");
        inv.add("gamemode");

        List<String> punish = new ArrayList();
        punish.add("kick");
        punish.add("ban");
        punish.add("minecraft:ban");
        punish.add("minecraft:kick");


        if (Permissions.checkPermission(p, "core.commandoverride")) {
            for (String Loop : inv) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.sendMessage(p, "Staff", "Please use build mode /build");
                }
            }

            for (String Loop : version2) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    p.sendMessage("This server is running CraftBukkit version\ngit-Spigot-ImaginearsCustom (MC: 1.13.2) (Implementing API version 1.13.2-ImaginearsCustom)");
                }
            }

            for (String Loop : plugins2) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    p.sendMessage(Chat.sendColorFree("&fPlugins (3): &aFaith&f, &aTrust&f, &aand a bit of pixie dust"));
                }
            }

            for (String Loop : punish) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.sendMessage(p, "Staff", "Please use the security menu /security");
                }
            }
        } else {
            for (String Loop : inv) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.sendMessage(p, "Staff", "Please use build mode /build");
                }
            }

            for (String Loop : punish) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.sendMessage(p, "Staff", "Please use the security menu /security");
                }
            }

            for (String Loop : plugins) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {
                    e.setCancelled(true);
                    p.sendMessage(Chat.sendColorFree("&fPlugins (3): &aFaith&f, &aTrust&f, &aand a bit of pixie dust"));
                }
            }

            for (String Loop : plugins2) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {
                    e.setCancelled(true);
                    p.sendMessage(Chat.sendColorFree("&fPlugins (3): &aFaith&f, &aTrust&f, &aand a bit of pixie dust"));
                }
            }


            for (String Loop : version) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    p.sendMessage("This server is running CraftBukkit version\ngit-Spigot-ImaginearsCustom (MC: 1.13.2) (Implementing API version 1.13.2-ImaginearsCustom)");
                }
            }

            for (String Loop : version2) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    p.sendMessage("This server is running CraftBukkit version\ngit-Spigot-ImaginearsCustom (MC: 1.13.2) (Implementing API version 1.13.2-ImaginearsCustom)");
                }
            }


            for (String Loop : question) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.permsError(p);
                }
            }


            for (String Loop : about) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.permsError(p);
                }
            }
        }
        if (!e.isCancelled()) {
            /*String command = e.getMessage().split(" ")[0];
            String htopic = Bukkit.getServer().getPluginCommand(command).toString();
            Console.Log(command.substring(1), Console.types.LOG);
            if (htopic == null)
            {
                if (Warps.checkWarp(command.substring(1))) {
                    if (Permissions.checkPermission(p, "core.warps." + Warps.getWarpPerm(command.substring(1)))) {
                        p.teleport(Warps.getWarp(command.substring(1)));
                        Chat.sendMessage(p, "Warps", "You've arrived at &b" + command.substring(1) + " &a(" + Warps.getWarpType(command.substring(1)) + ")");
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "You must have the rank of " + Rank.fromString(Warps.getWarpPerm(command.substring(1))).getPrefix() + " &cto use that warp.");
                    }
                } else {
                    Chat.sendMessage(p, "Command", "That command does not exist");
                }
                e.setCancelled(true);
            }*/
        }
    }

}
