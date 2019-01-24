package club.imaginears.core.events;

import club.imaginears.core.Core;
import club.imaginears.core.commands.Build;
import club.imaginears.core.commands.Vanish;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.InventoryManager;
import club.imaginears.core.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
        //p.teleport(new Location(Bukkit.getWorld("mk"), 64.5, 66.0, 1574.5, (float) -134.23317, (float) -0.30038348));
        p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRankPrefix(p) + " &7" + p.getName()));
        if (Build.checkBuildMode(p)) {
            p.setGameMode(GameMode.CREATIVE);
            InventoryManager.loadBuildInventory(p);
            Chat.sendMessage(p, "Staff", "You are still in buildmode!");
        } else {
            InventoryManager.loadPlayInventory(p);
            p.setGameMode(GameMode.ADVENTURE);
        }
        /* Vanish */

        if (p.hasPermission("core.vanish")) {
            if (Vanish.vanished.contains(p.getName())) {
                for (Player pls : Bukkit.getOnlinePlayers()) {
                    if (pls.hasPermission("core.vanish")) {
                        if (pls != p) {
                            Chat.sendMessage(pls, "StaffAnnounce", "&b" + p.getName() + " &ahas logged back in while vanished");
                        }
                    } else {
                        pls.hidePlayer(p);
                    }
                }

                Chat.sendMessage(p, "Staff", "You are still vanished! &b&lCurrently vanished: &a" + String.join(", ", Vanish.vanished));
            } else {
                if (!Vanish.vanished.isEmpty()) {
                    Chat.sendMessage(p, "Staff", "&b&lCurrently vanished: &a" + String.join(", ", Vanish.vanished));
                }

            }

        } else {
            for (String pls : Vanish.vanished) {
                p.hidePlayer(Bukkit.getPlayer(pls));
            }
        }
    }

}
