package club.imaginears.core.utils;

import club.imaginears.core.Core;
import club.imaginears.core.commands.Build;
import club.imaginears.core.commands.Vanish;
import club.imaginears.core.objects.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Players {

    public static Player findPlayer(String name) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().toLowerCase().contains(name.toLowerCase())) {
                return player;
            }
        }
        return null;
    }

    public static User getUser(String name) {
        for (User user : Core.getUsers()) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public static void joinSetup(Player p) {
        try {
            UUID uuid = p.getUniqueId();
            Core.deleteUser(uuid);
            Core.addUser(Players.createUser(uuid, p.getName()));
            MySQL.userSQLGrab(Core.getUser(uuid));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (p.getType() != EntityType.PLAYER) return;

        if (!MySQL.checkPlayerExists(p)) {
            MySQL.setupPlayer(p);
        }

        p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(p).prefix + " &7" + p.getName()));
    }


    public static User createUser(UUID uuid, String name) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
        Player pl = Bukkit.getPlayer(uuid);
        return new User(pl, p.getUniqueId(), p.getName(), Permissions.getOfflineRank(p), pl.getAddress().toString(), "WDW");
    }

}
