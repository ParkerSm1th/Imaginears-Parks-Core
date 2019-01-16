package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Nightvision implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.nv")) {
            if (args.length == 0) {
                if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    Chat.sendMessage(p, "Staff", "Nightvision disabled");
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000000, 500));
                    Chat.sendMessage(p, "Staff", "Nightvision enabled");
                }
            }
            if (args.length > 1) {
                if (Permissions.checkPermission(p, "core.nvothers")) {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/nv or /nv (Player)");
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/nv");
                }
            }
            if (args.length == 1) {
                if (Permissions.checkPermission(p, "core.nvothers")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        Chat.sendMessage(p, "Error", "That player is not online.");
                        return true;
                    }
                    if (target.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        target.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        Chat.sendMessage(target, "Staff", "Nightvision disabled");
                        Chat.sendMessage(p, "Staff", "Nightvision disabled for &b" + target.getName());
                    } else {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000000, 500));
                        Chat.sendMessage(target, "Staff", "Nightvision enabled");
                        Chat.sendMessage(p, "Staff", "Nightvision enabled for &b" + target.getName());
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/nv");
                }
            }
        }


        return true;
    }

}
