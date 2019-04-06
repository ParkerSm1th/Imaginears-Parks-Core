package club.imaginears.core.utils;

import club.imaginears.core.Core;
import club.imaginears.core.objects.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.Date;
import java.util.Random;

public class MySQL {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://198.24.160.26:3306/mysql",
                    "root",
                    "A9l0objZudcmslq9!");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Float getBalance(Player p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT balance FROM server.economy WHERE uuid=?");
            sql.setString(1, p.getUniqueId() + "");
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return (float) 0.0;
            }
            Float balance = result.getFloat("balance");
            result.close();
            sql.close();
            return balance;
        } catch (SQLException e) {
            e.printStackTrace();
            return (float) 0.0;
        }
    }

    public static Float getOfflineBalance(OfflinePlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT balance FROM server.economy WHERE uuid=?");
            sql.setString(1, p.getUniqueId() + "");
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return (float) 0.0;
            }
            Float balance = result.getFloat("balance");
            result.close();
            sql.close();
            return balance;
        } catch (SQLException e) {
            e.printStackTrace();
            return (float) 0.0;
        }
    }

    public static void setBalance(Player u, Float bal) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT balance FROM server.economy WHERE uuid=?");
            sql.setString(1, u.getUniqueId().toString());
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
            }
            Float oldBalance = result.getFloat("balance");
            result.close();
            sql.close();
            PreparedStatement pl = connection.prepareStatement("UPDATE server.economy SET balance = ?, last_balance = ? WHERE uuid = ?");
            pl.setFloat(1, bal);
            pl.setFloat(2, oldBalance);
            pl.setString(3, u.getUniqueId().toString());
            pl.execute();
            pl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setOfflineBalance(OfflinePlayer u, Float bal) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT balance FROM server.economy WHERE uuid=?");
            sql.setString(1, u.getUniqueId().toString());
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
            }
            Float oldBalance = result.getFloat("balance");
            result.close();
            sql.close();
            PreparedStatement pl = connection.prepareStatement("UPDATE server.economy SET balance = ?, last_balance = ? WHERE uuid = ?");
            pl.setFloat(1, bal);
            pl.setFloat(2, oldBalance);
            pl.setString(3, u.getUniqueId().toString());
            pl.execute();
            pl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addToBalance(Player u, Float amount) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT balance FROM server.economy WHERE uuid=?");
            sql.setString(1, u.getUniqueId().toString());
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
            }
            Float oldBalance = result.getFloat("balance");
            result.close();
            sql.close();
            Float bal = oldBalance + amount;
            PreparedStatement pl = connection.prepareStatement("UPDATE server.economy SET balance = ?, last_balance = ? WHERE uuid = ?");
            pl.setFloat(1, bal);
            pl.setFloat(2, oldBalance);
            pl.setString(3, u.getUniqueId().toString());
            pl.execute();
            pl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void subtractFromBalance(Player u, Float amount) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT balance FROM server.economy WHERE uuid=?");
            sql.setString(1, u.getUniqueId().toString());
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
            }
            Float oldBalance = Float.parseFloat(result.getString("balance"));
            result.close();
            sql.close();
            Float bal = oldBalance - amount;
            PreparedStatement pl = connection.prepareStatement("UPDATE server.economy SET balance = ?, last_balance = ? WHERE uuid = ?");
            pl.setFloat(1, bal);
            pl.setFloat(2, oldBalance);
            pl.setString(3, u.getUniqueId().toString());
            pl.execute();
            pl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPin(Player p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("DELETE FROM server.panelpins WHERE uuid=?");
            sql.setString(1, p.getUniqueId().toString() + "");
            sql.execute();
            sql.close();
            char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");
            for (int i = 0; i < 5; i++)
                sb.append(chars[rnd.nextInt(chars.length)]);
            String pin = sb.toString();
            PreparedStatement pl = connection.prepareStatement("INSERT INTO server.panelpins (uuid, pin, username, role) VALUES (?,?,?,?)");
            pl.setString(1, p.getUniqueId().toString());
            pl.setString(2, pin);
            pl.setString(3, p.getName());
            User u = Core.getUser(p.getUniqueId());
            if (u.getRank().equals(Rank.CASTMEMBER)) {
                if (p.hasPermission("core.buildmode")) {
                    pl.setFloat(4, (float) 4.5);
                } else {
                    pl.setInt(4, u.getRank().getRankLadder());
                }
            } else {
                pl.setInt(4, u.getRank().getRankLadder());
            }
            pl.execute();
            pl.close();
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {

                public void run() {
                    try (Connection connection = getConnection()) {
                        PreparedStatement sql3 = connection.prepareStatement("DELETE FROM server.panelpins WHERE uuid=?");
                        sql3.setString(1, p.getUniqueId().toString());
                        sql3.execute();
                        sql3.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }, 1800L);
            return pin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Transaction getMostRecentTransactions(Player p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM server.transactions WHERE from_uuid=? OR to_uuid=? ORDER BY id DESC LIMIT 0, 1");
            sql.setString(1, p.getUniqueId().toString() + "");
            sql.setString(2, p.getUniqueId().toString() + "");
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return new Transaction(Transaction.transactionType.ERROR, "", "", (float) 0.0);
            }
            String from_uuid = result.getString("from_uuid");
            String to_uuid = result.getString("to_uuid");
            String type = result.getString("type");
            Float amount = result.getFloat("amount");
            result.close();
            sql.close();
            if (type.equalsIgnoreCase("PAY")) {
                if (to_uuid.equalsIgnoreCase(p.getUniqueId() + "")) {
                    return new Transaction(Transaction.transactionType.PAYRECIEVE, p.getUniqueId().toString(), from_uuid, amount);
                } else {
                    return new Transaction(Transaction.transactionType.PAYSEND, p.getUniqueId().toString(), to_uuid, amount);
                }
            }

            if (type.equalsIgnoreCase("CHARGE")) {
                return new Transaction(Transaction.transactionType.CHARGE, p.getUniqueId().toString(), to_uuid, amount);
            }

            if (type.equalsIgnoreCase("REWARD")) {
                return new Transaction(Transaction.transactionType.REWARD, p.getUniqueId().toString(), "SERVER", amount);
            }
            return new Transaction(Transaction.transactionType.ERROR, "", "", (float) 0.0);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void logTransaction(String UUID1, String UUID2, String transType, Float amount) {
        try (Connection connection = getConnection()) {
            PreparedStatement pl = connection.prepareStatement("INSERT INTO server.transactions (from_uuid, to_uuid, amount, type, time) VALUES (?, ?, ?, ?, ?)");
            pl.setString(1, UUID1);
            pl.setString(2, UUID2);
            pl.setFloat(3, amount);
            pl.setString(4, transType);
            Date date = new Date();
            long timeMilli = date.getTime();
            pl.setString(5, Long.toString(timeMilli));
            pl.execute();
            pl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPlayerData(Player p, String val) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM server.player_data WHERE uuid=?");
            sql.setString(1, p.getUniqueId() + "");
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return null;
            }
            String item = result.getString(val);
            result.close();
            sql.close();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPlayerDataUUID(String uuid, String val) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM server.player_data WHERE uuid=?");
            sql.setString(1, uuid);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return null;
            }
            String item = result.getString(val);
            result.close();
            sql.close();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateRank(Player u, Rank rank) {
        try (Connection connection = getConnection()) {
            PreparedStatement pl = connection.prepareStatement("UPDATE server.player_data SET rank = ? WHERE uuid = ?");
            pl.setInt(1, rank.getRankLadder());
            pl.setString(2, u.getUniqueId().toString());
            pl.execute();
            pl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkPlayerExists(Player p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT id FROM server.player_data WHERE uuid=?");
            sql.setString(1, p.getUniqueId() + "");
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String item = result.getString("id");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean checkPlayerExistsOffline(OfflinePlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT id FROM server.player_data WHERE uuid=?");
            sql.setString(1, p.getUniqueId() + "");
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String item = result.getString("id");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean checkDiscordCode(String code) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM panel.discord_link_attempts WHERE code=?");
            sql.setString(1, code);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String mc = result.getString("minecraft_uuid");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DiscordUser getDiscordUser(String code) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM panel.discord_link_attempts WHERE code=?");
            sql.setString(1, code);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return null;
            }
            String id = result.getString("discord_id");
            String username = result.getString("discord_username");
            String indentifier = result.getString("discord_identifier");
            DiscordUser duser = new DiscordUser(id, username, indentifier, code);
            result.close();
            sql.close();
            return duser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateDiscordUser(String code, Player p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("UPDATE panel.discord_link_attempts SET minecraft_uuid = ?, minecraft_username = ? WHERE code = ?");
            sql.setString(1, p.getUniqueId().toString());
            sql.setString(2, p.getName());
            sql.setString(3, code);
            sql.execute();
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static Boolean checkPlayerBanned(String uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT id FROM server.bans WHERE uuid=? AND active = 1");
            sql.setString(1, uuid);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String item = result.getString("id");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean checkPlayerOnline(String uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM server.player_data WHERE uuid=? AND online = 1");
            sql.setString(1, uuid);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String item = result.getString("online");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void userLogOff(Player p) {
        try (Connection connection = getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void logKick(Kick kick) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("INSERT INTO server.punishment_log (uuid, username, type, reason, length, op, time, punishid, active) VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, kick.getUser().getUniqueId().toString());
            sql.setString(2, kick.getUser().getName());
            sql.setString(3, "KICK");
            sql.setString(4, kick.getReason());
            sql.setString(5, "0");
            sql.setString(6, kick.getOp().getUniqueId().toString());
            sql.setString(7, kick.getTime());
            sql.setString(8, kick.getPid());
            sql.setString(9, "1");
            sql.execute();
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void userSQLGrab(User u) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM server.economy WHERE uuid=?");
            sql.setString(1, u.getUniqueId() + "");
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
            }
            Float balance = result.getFloat("balance");
            result.close();
            PreparedStatement sql3 = connection.prepareStatement("SELECT * FROM server.player_data WHERE uuid=?");
            sql3.setString(1, u.getUniqueId() + "");
            ResultSet result3 = sql3.executeQuery();
            if (!result3.next()) {
                result3.close();
                sql3.close();
            }
            u.setFirstJoin(result3.getString("first_join"));
            result3.close();
            PreparedStatement sql2 = connection.prepareStatement("UPDATE server.player_data SET last_server = 'wdw' WHERE uuid = ?");
            sql2.setString(1, u.getUniqueId().toString());
            sql2.execute();
            u.setBalance(balance);
            if (checkPlayerBanned(u.getUniqueId().toString())) {
                PreparedStatement ban = connection.prepareStatement("SELECT * FROM server.bans WHERE uuid=? AND active = 1");
                ban.setString(1, u.getUniqueId() + "");
                ResultSet banresult = ban.executeQuery();
                if (!banresult.next()) {
                    banresult.close();
                    sql.close();
                }
                u.setBanned(true);
                Ban userBan = new Ban(u.getUniqueId().toString(), u.getName(), banresult.getString("reason"), banresult.getString("length"), banresult.getString("time"), banresult.getString("op"), banresult.getString("punishid"));
                u.setBan(userBan);
                banresult.close();
                ban.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static Ban getBan(String uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement ban = connection.prepareStatement("SELECT * FROM server.bans WHERE uuid=? AND active = 1");
            ban.setString(1, uuid);
            ResultSet result = ban.executeQuery();
            if (!result.next()) {
                result.close();
                ban.close();
                return null;
            }
            String username = result.getString("username");
            String reason = result.getString("reason");
            String length = result.getString("length");
            String time = result.getString("time");
            String op = result.getString("op");
            String punishid = result.getString("punishid");
            Ban userBan = new Ban(uuid, username, reason, length, time, op, punishid);
            result.close();
            ban.close();
            return userBan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setupPlayer(Player p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("INSERT INTO server.economy (uuid, balance, last_balance) VALUES (?,?,?)");
            sql.setString(1, p.getUniqueId().toString());
            sql.setFloat(2, (float) 0.0);
            sql.setFloat(3, (float) 0.0);
            sql.execute();
            sql.close();

            PreparedStatement pl = connection.prepareStatement("INSERT INTO server.player_data (username, uuid, rank, first_join, online, last_server, webkey, build_mode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pl.setString(1, p.getName());
            pl.setString(2, p.getUniqueId().toString());
            pl.setInt(3, Permissions.getRankUUID(p.getUniqueId().toString()).getRankLadder());
            pl.setLong(4, p.getFirstPlayed());
            pl.setString(5, "1");
            pl.setString(6, "wdw");
            pl.setString(7, "null");
            pl.setString(8, "0");
            pl.execute();
            pl.close();
            Console.Log("First join sql setup for player " + p.getName(), Console.types.DEBUG);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
