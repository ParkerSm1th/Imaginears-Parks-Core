package club.imaginears.core.utils;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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

    public static User createUser(UUID uuid, String name) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
        Player pl = Bukkit.getPlayer(uuid);
        return new User(pl, p.getUniqueId(), p.getName(), Permissions.getOfflineRank(p), pl.getAddress().toString(), "WDW");
    }

}
