package club.imaginears.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Build implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!club.imaginears.core.commands.Build.buildMode.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (!club.imaginears.core.commands.Build.buildMode.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
            return;
        }

    }

}
