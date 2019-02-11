package club.imaginears.core.objects;


import club.imaginears.core.utils.Chat;
import club.imaginears.core.utils.MySQL;
import club.imaginears.core.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class User {
    private org.bukkit.entity.Player player;
    private UUID uuid;
    private String username;
    private Rank rank = Rank.GUEST;
    private String address;
    private String server;
    private Float balance;
    private long loginTime = System.currentTimeMillis();

    public User(Player player, UUID uuid, String username, Rank rank, String address, String server) {
        this.player = player;
        this.uuid = uuid;
        this.rank = rank;
        this.username = username;
        this.address = address;
        this.server = server;
    }


    public UUID getUniqueId() {
        return uuid;
    }

    public String getName() {
        return username;
    }

    public String getPrefix() {
        return this.rank.prefix;
    }

    public String getChatColor() {
        return this.rank.chatColor;
    }

    public Rank getRank() {
        return rank;
    }

    public String getAddress() {
        return address;
    }

    public String getServer() {
        return server;
    }

    public Float getBalance() {
        return balance;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
        MySQL.setBalance(this.player, balance);
    }

    public void addToBalance(Float balance) {
        this.balance = this.balance + balance;
        MySQL.addToBalance(this.player, balance);
    }

    public void subtractFromBalance(Float balance) {
        this.balance = this.balance - balance;
        MySQL.subtractFromBalance(this.player, balance);
    }

    public Transaction getMostRecentTransaction() {
        return MySQL.getMostRecentTransactions(this.player);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public long getLoginTime() {
        return loginTime;
    }


}