package club.imaginears.core.events;

import club.imaginears.core.commands.Build;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.InventoryManager;
import club.imaginears.core.utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.getType() != EntityType.PLAYER) return;
        MySQL.userLogOff(p);

        if (Build.checkBuildMode(p)) {
            InventoryManager.saveBuildInventory(p);
        } else {
            InventoryManager.savePlayInventory(p);
        }
        e.setQuitMessage(null);
    }

}
