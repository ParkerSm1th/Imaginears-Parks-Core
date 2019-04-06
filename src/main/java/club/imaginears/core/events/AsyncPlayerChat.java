package club.imaginears.core.events;

import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.MySQL;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Players;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {

        if (!e.isCancelled()) {
            Player p = e.getPlayer();
            User user = Players.getUser(p.getName());
            BaseComponent start = new TextComponent(new TextComponent(ChatColor.translateAlternateColorCodes('&', user.getPrefix() + "&7 " + user.getName())));

            start.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Chat.sendColorFree("&bRank: " + user.getRank().getPrefix() + "\n&bBalance: &a$" + user.getBalance() + "\n" + "&e&lClick here to view their profile")).create()));
            start.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/profile " + user.getName()));

            BaseComponent msg = new TextComponent(new TextComponent(ChatColor.translateAlternateColorCodes('&',   "&7: " + user.getChatColor() + e.getMessage())));

            for (Player all : Bukkit.getOnlinePlayers()) {
                all.spigot().sendMessage(start, msg);
            }
            e.setCancelled(true);
        }

    }
}
