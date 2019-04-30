package club.imaginears.core.commands;

import club.imaginears.core.Core;
import club.imaginears.core.objects.Rank;
import club.imaginears.core.objects.User;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.MySQL;
import club.imaginears.core.utils.Permissions;
import club.imaginears.core.utils.Players;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UpdateRank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            CommandSender p = sender;

            if (args.length > 2 || args.length < 2) {
                p.sendMessage("/updaterank (Player Name) (Rank)");
            }
            if (args.length == 2) {
                String itemname = args[1].toLowerCase();
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                User user = Players.getUser(target.getName());
                p.sendMessage("Trying to update rank");
                if (itemname.contains("guest")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set default");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("default"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("default"));
                }
                if (itemname.contains("silver")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set silver");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("silver"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("silver"));
                }
                if (itemname.contains("gold")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set gold");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("gold"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("gold"));
                }
                if (itemname.contains("platinum+")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum+");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("platinum+"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("platinum+"));
                    return true;
                }
                if (itemname.contains("platinum")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("platinum"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("platinum"));
                }
                if (itemname.contains("special guest")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set specialguest");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("specialguest"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("specialguest"));
                }
                if (itemname.contains("security")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set security");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("security"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("security"));
                }
                if (itemname.contains("character")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set character");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("character"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("character"));
                }
                if (itemname.contains("photopass")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set photopass");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("photopass"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("photopass"));
                }
                if (itemname.contains("cast member")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set castmember");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("castmember"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("castmember"));
                }
                if (itemname.contains("coordinator")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set coordinator");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("coordinator"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("coordinator"));
                }
                if (itemname.contains("developer")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set developer");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("developer"));
                    //user.setRank(club.imaginears.core.objects.Rank.fromString("developer"));
                }
                if (itemname.contains("manager")) {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set manager");
                    MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("manager"));
                    //user.setRank(Rank.fromString("manager"));
                }


            }

            return true;
        } else {
            Player p = (Player) sender;
            User u = Core.getUser(p.getUniqueId());

            if (Permissions.checkPermissionMsg(p, "core.updaterank")) {
                if (args.length > 2 || args.length < 2) {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/updaterank (Player Name) (Rank)");
                }
                if (args.length == 2) {
                    String itemname = args[1].toLowerCase();
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    User user = Players.getUser(target.getName());
                    Chat.sendMessage(p, "Ranking", "Trying to update rank");
                    Player targetonline = Bukkit.getPlayer(args[0]);

                    if (itemname.contains("guest")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set default");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("default").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("default"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("default"));
                    }
                    if (itemname.contains("silver")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set silver");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("silver").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("silver"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("silver"));
                    }
                    if (itemname.contains("gold")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set gold");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("gold").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("gold"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("gold"));
                    }
                    if (itemname.contains("platinum+")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum+");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("platinum+").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("platinum+"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("platinum+"));
                        if (targetonline != null) {
                            targetonline.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(targetonline).prefix + " &7" + targetonline.getName()));
                            targetonline.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(targetonline).prefix + " &7" + targetonline.getName()));
                            targetonline.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(targetonline).prefix + " &7" + targetonline.getName()));
                        }
                        return true;
                    }
                    if (itemname.contains("platinum")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set platinum");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("platinum").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("platinum"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("platinum"));
                    }
                    if (itemname.contains("special guest")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set specialguest");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("specialguest").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("specialguest"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("specialguest"));
                    }
                    if (itemname.contains("security")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set security");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("security").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("security"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("security"));
                    }
                    if (itemname.contains("character")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set character");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("character").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("character"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("character"));
                    }
                    if (itemname.contains("photopass")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set photopass");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("photopass").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("photopass"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("photopass"));
                    }
                    if (itemname.contains("cast member")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set castmember");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("castmember").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("castmember"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("castmember"));
                    }
                    if (itemname.contains("coordinator")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set coordinator");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("coordinator").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("coordinator"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("coordinator"));
                    }
                    if (itemname.contains("developer")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set developer");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("developer").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("developer"));
                        //user.setRank(club.imaginears.core.objects.Rank.fromString("developer"));
                    }
                    if (itemname.contains("manager")) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "perms user " + target.getName().toString() + " parent set manager");
                        Chat.sendMessage(p, "Permissions", "Successfully updated " + target.getName().toString() + "'s rank to " + club.imaginears.core.objects.Rank.fromString("manager").getPrefix());
                        MySQL.updateRankOffline(UUID.fromString(args[0]), Rank.fromString("manager"));
                        //user.setRank(Rank.fromString("manager"));
                    }
                    if (targetonline != null) {
                        targetonline.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(targetonline).prefix + " &7" + targetonline.getName()));
                        targetonline.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(targetonline).prefix + " &7" + targetonline.getName()));
                        targetonline.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Permissions.getRank(targetonline).prefix + " &7" + targetonline.getName()));
                    }

                }

            }

            return true;
        }
    }

}
