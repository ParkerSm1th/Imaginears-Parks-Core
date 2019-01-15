package club.imaginears.core.events;

import club.imaginears.core.Core;
import club.imaginears.core.commands.Vanish;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getType() != EntityType.PLAYER) return;

        Chat.sendMessage(p, "Welcome", "Welcome to &b&lWalt Disney World&r&a!");
        e.setJoinMessage(null);
        /* Vanish */
        if (p.hasPermission("core.vanish")) {
            if (Vanish.vanished.contains(p.getName())) {
                for (Player pls : Bukkit.getOnlinePlayers()) {
                    if (pls.hasPermission("core.vanish")) {
                        if (pls != p) {
                            Chat.sendMessage(pls, "Staff", "&b" + p.getName() + " &ahas logged back in while vanished");
                        }
                    } else {
                        pls.hidePlayer(p);
                    }
                }

                Chat.sendMessage(p, "Staff", "You are still vanished! &b&lAlso currently vanished: &a" + String.join(", ", Vanish.vanished));
            } else {
                Chat.sendMessage(p, "Staff", "&b&lCurrently vanished: &a" + String.join(", ", Vanish.vanished));
            }

        } else {
            for (String pls : Vanish.vanished) {
                p.hidePlayer(Bukkit.getPlayer(pls));
            }
        }
    }

}
