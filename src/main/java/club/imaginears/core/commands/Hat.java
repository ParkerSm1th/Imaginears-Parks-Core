package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        User u = Core.getUser(p.getUniqueId());

        if (Permissions.checkPermissionMsg(p, "core.hat")) {

            ItemStack hand = p.getItemInHand();
            if (hand.getType() == Material.DIAMOND_SHOVEL) {
                ItemStack head = p.getInventory().getHelmet();
                p.getInventory().setHelmet(hand);
                p.getInventory().remove(hand);
                if (head != null) {
                    p.getInventory().addItem(head);
                }
                Chat.sendMessage(p, "Costume", "Equipped your hat!");
            } else {
                Chat.sendError(p, Chat.ChatErrors.COMMON, "You can't equip this as a hat!");
            }

        }

        return true;
    }

}
