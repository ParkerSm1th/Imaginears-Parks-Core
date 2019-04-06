package club.imaginears.core.events;

import club.imaginears.core.Core;
import club.imaginears.core.commands.Build;
import club.imaginears.core.commands.Vanish;
import club.imaginears.core.objects.Ban;
import club.imaginears.core.utils.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(AsyncPlayerPreLoginEvent event) {

        if (MySQL.checkPlayerBanned(event.getUniqueId().toString())) {
            Ban ban = MySQL.getBan(event.getUniqueId().toString());
            /*Date date = new Date(ban.getTime());*/
            String length = ban.getLength();
            if (ban.getLength().equals("0")) {
                length = "Permanent";
            }
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Chat.sendColorFree("\n&cPUNISHMENT &7» &aYou are currently banned from Imaginears Club!\n\n&cReason: &b" + ban.getReason() + "\n&cLength: &b" + length + "\n&cDate Banned: &b" + CalendarUtils.ConvertMilliSecondsToFormattedDate(ban.getTime()) + "\n\n&aTo appeal visit: https://imaginears.club/hub/appeal\n\n&cPunishment ID: &b" + ban.getPid()));
        }

        if (WhitelistManager.checkStatus()) {
            if (!WhitelistManager.checkPlayer(event.getUniqueId().toString())) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', "&bSecurity" + "&7 » &aWe are experiencing some pixie dust on &bWDW &aplease check back later!"));
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getType() != EntityType.PLAYER) return;
        if (!MySQL.checkPlayerExists(p)) {
            MySQL.setupPlayer(p);
        }
        try {
            UUID uuid = e.getPlayer().getUniqueId();
            Core.deleteUser(uuid);
            Core.addUser(Players.createUser(uuid, e.getPlayer().getName()));
            MySQL.userSQLGrab(Core.getUser(uuid));
        } catch (Exception ex) {
            ex.printStackTrace();
            e.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', "&bSecurity" + "&7 » &cAn error occurred while you were logging in. You may now rejoin!"));
        }





        Chat.sendMessage(p, "Welcome", "Welcome to &b&lWalt Disney World&r&a!");
        e.setJoinMessage(null);
        //p.teleport(new Location(Bukkit.getWorld("mk"), 64.5, 66.0, 1574.5, (float) -134.23317, (float) -0.30038348));
        p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(p).prefix + " &7" + p.getName()));
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
