package club.imaginears.core.commands;

import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.tp")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/tp (Online player)");
                } else {
                    if (TPToggle.tpoff.contains(target.getName()) && !Permissions.checkPermission(p, "core.tpoverride")) {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "That player has meeting up toggled off.");
                        return true;
                    }
                    p.teleport(target);
                    Chat.sendMessage(p, "Staff", "You have met up with &b" + target.getName());
                }
            }
            if (args.length > 2 || args.length < 1) {
                if (Permissions.checkPermission(p, "core.tpothers")) {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/tp (Online player) [Online player]");
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/tp (Online player)");
                }
            }
            if (args.length == 2) {
                if (Permissions.checkPermission(p, "core.tpothers")) {
                    Player target1 = Bukkit.getPlayer(args[0]);
                    Player target2 = Bukkit.getPlayer(args[1]);
                    if (target1 == null || target2 == null) {
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/tp (Online player) [Online player]");
                    } else {
                        if (TPToggle.tpoff.contains(target1.getName()) || TPToggle.tpoff.contains(target2.getName()) && !Permissions.checkPermission(p, "core.tpoverride")) {
                            Chat.sendError(p, Chat.ChatErrors.COMMON, "One of those players has meeting up toggled off.");
                            return true;
                        }
                        target1.teleport(target2);
                        Chat.sendMessage(p, "Staff", "Sent &b" + target1.getName() + " &ato meet up with &b" + target2.getName());
                    }

                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/tp (Online player)");
                }
            }

        }


        return true;
    }

}
