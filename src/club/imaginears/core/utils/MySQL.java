package club.imaginears.core.utils;

import club.imaginears.core.objects.Transaction;
import club.imaginears.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.Date;

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
            sql.close();
            u.setBalance(balance);
        } catch (SQLException e) {
            e.printStackTrace();

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
