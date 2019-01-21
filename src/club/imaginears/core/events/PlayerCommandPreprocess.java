package club.imaginears.core.events;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommandPreprocess implements Listener {

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent e) {
        String[] msg = e.getMessage().split(" ");
        Player p = e.getPlayer();

        List<String> plugins = new ArrayList();
        plugins.add("pl");
        plugins.add("plugins");
        plugins.add("bukkit:pl");
        plugins.add("bukkit:plugins");
        plugins.add("plugins");

        List<String> version = new ArrayList();
        version.add("ver");
        version.add("version");
        version.add("bukkit:ver");
        version.add("bukkit:version");

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
        if (Permissions.checkPermission(p, "core.commandoverride")) {
            for (String Loop : inv) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.sendMessage(p, "Staff", "Please use build mode /build");
                }
            }
        } else {
            for (String Loop : inv) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.sendMessage(p, "Staff", "Please use build mode /build");
                }
            }

            for (String Loop : plugins) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.permsError(p);
                }
            }


            for (String Loop : version) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    Chat.permsError(p);
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
    }

}
