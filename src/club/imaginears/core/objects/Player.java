package club.imaginears.core.objects;


import java.util.UUID;

public class Player {
    private UUID uuid;
    private String username;
    private String rank = "default";
    private String address;
    private String server;
    private UUID bungeeID;
    private long loginTime = System.currentTimeMillis();
    private long onlineTime = 0;

    public Player(UUID uuid, String username, String address, String server, UUID bungeeID) {
        this.uuid = uuid;
        this.username = username;
        this.address = address;
        this.server = server;
        this.bungeeID = bungeeID;
    }


    public UUID getUniqueId() {
        return uuid;
    }

    public String getName() {
        return username;
    }

    public String getRank() {
        return rank;
    }

    public String getAddress() {
        return address;
    }

    public String getServer() {
        return server;
    }

    public UUID getBungeeID() {
        return bungeeID;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public long getLoginTime() {
        return loginTime;
    }


}