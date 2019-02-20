package club.imaginears.core.events;

import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Players;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {

    @EventHandler(priority= EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();
        User user = Players.getUser(p.getName());

        e.setFormat(ChatColor.translateAlternateColorCodes('&', user.getPrefix() + "&7 " + user.getName() + ": " + user.getChatColor() + e.getMessage()));

    }
}
