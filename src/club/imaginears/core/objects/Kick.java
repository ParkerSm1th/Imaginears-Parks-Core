package club.imaginears.core.objects;

import club.imaginears.core.utils.MySQL;
import club.imaginears.core.utils.ShopSigns;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Date;
import java.util.Random;

public class Kick {

    private User user;
    private String reason;
    private User op;
    private String time;
    private String pid;


    public Kick(User user, String reason, User op) {
        this.user = user;
        this.reason = reason;
        this.op = op;
    }

    public User getUser() {
        return this.user;
    }

    public String getReason() {
        return this.reason;
    }

    public User getOp() {
        return this.op;
    }

    public void init() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");
        for (int i = 0; i < 5; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);
        this.pid = sb.toString();
        //Date date = new Date();
        //long timeMilli = date.getTime();
        //this.time = Long.toString(timeMilli);

    }

    public String getPid() {
        return this.pid;
    }

    public String getTime() {
        return this.time;
    }



}
