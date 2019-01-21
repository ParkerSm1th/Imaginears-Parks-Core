package club.imaginears.core.events;

import club.imaginears.core.utils.Permissions;
import mc.cyberplex.ChatManager.ChatManager;
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
        e.setFormat(ChatColor.translateAlternateColorCodes('&', Permissions.getRankPrefix(p) + "&7 " + p.getDisplayName() + ": " + Permissions.getChatColor(p) + e.getMessage()));

    }
}
