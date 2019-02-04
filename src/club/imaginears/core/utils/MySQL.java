package club.imaginears.core.utils;

import club.imaginears.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;

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
            sql.setInt(2, 0);
            sql.setInt(3, 0);
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
