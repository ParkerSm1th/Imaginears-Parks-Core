package club.imaginears.core.commands;

import club.imaginears.core.objects.Rank;
import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.Console;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WhitelistManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        if (Permissions.checkPermissionMsg(p, "core.whitelistmanager")) {
            if (args.length > 0) {
                String subcommand = args[0];

                if (subcommand.equalsIgnoreCase("list")) {
                    if (club.imaginears.core.utils.WhitelistManager.checkStatus()) {
                        Chat.sendMessage(p, "Whitelist", "Below is the current rank and list of players, if the title of the section is red that means that filter is disabled, if it is green it is enabled.");
                        if (club.imaginears.core.utils.WhitelistManager.checkRanks()) {
                            p.sendMessage(Chat.sendColorFree("&aRank: " + Permissions.getRankFromLadder(club.imaginears.core.utils.WhitelistManager.getRank()).getPrefix()));
                        } else {
                            p.sendMessage(Chat.sendColorFree("&cRank: " + Permissions.getRankFromLadder(club.imaginears.core.utils.WhitelistManager.getRank()).getPrefix()));
                        }
                        List<String> players = new ArrayList<>();
                        Console.Log(club.imaginears.core.utils.WhitelistManager.getPlayers() + " ", Console.types.DEBUG);
                        for (String uuid : club.imaginears.core.utils.WhitelistManager.getPlayers()) {
                            players.add(Bukkit.getOfflinePlayer(uuid).getName());
                        }
                        if (club.imaginears.core.utils.WhitelistManager.checkPlayers()) {
                            p.sendMessage(Chat.sendColorFree("&aPlayers: &b") + String.join("&a, &b", players));
                        } else {
                            p.sendMessage(Chat.sendColorFree("&cPlayers: &b") + String.join("&c, &b", players));
                        }
                    } else {
                        Chat.sendMessage(p, "Whitelist", "&cThe whitelist is currently off.");
                    }
                    return true;
                }

                if (subcommand.equalsIgnoreCase("on")) {
                    if (club.imaginears.core.utils.WhitelistManager.checkStatus()) {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "The whitelist is already enabled");
                    } else {
                        club.imaginears.core.utils.WhitelistManager.on();
                        Chat.sendMessage(p, "Whitelist", "Enabled whitelist");
                    }
                    return true;
                }

                if (subcommand.equalsIgnoreCase("off")) {
                    if (!club.imaginears.core.utils.WhitelistManager.checkStatus()) {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "The whitelist is already disabled");
                    } else {
                        club.imaginears.core.utils.WhitelistManager.off();
                        Chat.sendMessage(p, "Whitelist", "Disabled whitelist");
                    }
                    return true;
                }

                if (subcommand.equalsIgnoreCase("add")) {
                    if (club.imaginears.core.utils.WhitelistManager.checkPlayers()) {
                        if (args.length == 2) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            if (club.imaginears.core.utils.WhitelistManager.checkPlayerList(target.getUniqueId().toString())) {
                                Chat.sendError(p, Chat.ChatErrors.COMMON, "That player is already on the whitelist");
                            } else {
                                club.imaginears.core.utils.WhitelistManager.addPlayer(target.getUniqueId().toString());
                                Chat.sendMessage(p, "Whitelist", "Added &b" + target.getName() + "&a to the whitelist");
                            }
                        } else {
                            Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                        }
                        return true;
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "Players are not enabled for the whitelist");
                        return true;
                    }
                }

                if (subcommand.equalsIgnoreCase("remove")) {
                    if (club.imaginears.core.utils.WhitelistManager.checkPlayers()) {
                        if (args.length == 2) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            if (!club.imaginears.core.utils.WhitelistManager.checkPlayerList(target.getUniqueId().toString())) {
                                Chat.sendError(p, Chat.ChatErrors.COMMON, "That player is not on the whitelist");
                            } else {
                                club.imaginears.core.utils.WhitelistManager.removePlayer(target.getUniqueId().toString());
                                Chat.sendMessage(p, "Whitelist", "Removed &b" + target.getName() + "&a from the whitelist");
                            }
                        } else {
                            Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                        }
                        return true;
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "Players are not enabled for the whitelist");
                        return true;
                    }
                }

                if (subcommand.equalsIgnoreCase("players")) {
                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("on")) {
                            if (club.imaginears.core.utils.WhitelistManager.checkPlayers()) {
                                Chat.sendError(p, Chat.ChatErrors.COMMON, "Players are already enabled for the whitelist");
                            } else {
                                club.imaginears.core.utils.WhitelistManager.enablePlayers();
                                Chat.sendMessage(p, "Whitelist", "Enabled players for the whitelist");
                            }
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("off")) {
                            if (!club.imaginears.core.utils.WhitelistManager.checkPlayers()) {
                                Chat.sendError(p, Chat.ChatErrors.COMMON, "Players are already disabled for the whitelist");
                            } else {
                                club.imaginears.core.utils.WhitelistManager.disablePlayers();
                                Chat.sendMessage(p, "Whitelist", "Disabled players for the whitelist");
                            }
                            return true;
                        }
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                    }
                    return true;
                }

                if (subcommand.equalsIgnoreCase("ranks")) {
                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("on")) {
                            if (club.imaginears.core.utils.WhitelistManager.checkRanks()) {
                                Chat.sendError(p, Chat.ChatErrors.COMMON, "Ranks are already enabled for the whitelist");
                            } else {
                                club.imaginears.core.utils.WhitelistManager.enableRanks();
                                Chat.sendMessage(p, "Whitelist", "Ranks players for the whitelist");
                            }
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("off")) {
                            if (!club.imaginears.core.utils.WhitelistManager.checkRanks()) {
                                Chat.sendError(p, Chat.ChatErrors.COMMON, "Ranks are already disabled for the whitelist");
                            } else {
                                club.imaginears.core.utils.WhitelistManager.disableRanks();
                                Chat.sendMessage(p, "Whitelist", "Ranks players for the whitelist");
                            }
                            return true;
                        }
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                    }
                    return true;
                }

                if (subcommand.equalsIgnoreCase("setrank")) {
                    if (club.imaginears.core.utils.WhitelistManager.checkRanks()) {
                        if (args.length == 2) {
                            Rank rank = Rank.fromString(args[1]);
                            if (rank.getRankLadder() == -1) {
                                Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager setrank (valid rank)");
                            } else {
                                club.imaginears.core.utils.WhitelistManager.setRank(rank.getRankLadder());
                                Chat.sendMessage(p, "Whitelist", "Set the rank to " + rank.getPrefix());
                            }
                            return true;
                        } else {
                            Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                        }
                        return true;
                    } else {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "Ranks are not enabled for the whitelist");
                        return true;
                    }
                }

                Chat.sendError(p, Chat.ChatErrors.INVALIDARG, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");
                return true;
            }

            Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/whitelistmanager (On/Off) or add (Username) or remove (Username) or ranks (On/Off) or players (on/off) or setrank (rank)");

        }

        return true;
    }

}
