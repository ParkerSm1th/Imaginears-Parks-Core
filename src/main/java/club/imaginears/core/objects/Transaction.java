package club.imaginears.core.objects;

import club.imaginears.core.utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Transaction {

    public enum transactionType {
        REWARD, CHARGE, PAYRECIEVE, PAYSEND, ERROR;
    }

    private transactionType type;
    private String user_uuid;
    private String other;
    private Float amount;

    public Transaction(transactionType type, String user_uuid, String other, Float amount) {
        this.type = type;
        this.user_uuid = user_uuid;
        this.other = other;
        this.amount = amount;
    }

    public transactionType getType() {
        return type;
    }

    public String getUser() {
        return user_uuid;
    }

    public String getOther() {
        return other;
    }

    public Float getAmount() {
        return amount;
    }

    public void logTransaction() {
        switch(this.type) {
            case REWARD:
                MySQL.logTransaction("SERVER", this.user_uuid, "REWARD", amount);
                break;
            case CHARGE:
                MySQL.logTransaction(this.user_uuid, this.other, "CHARGE", amount);
                break;
            case PAYRECIEVE:
                MySQL.logTransaction(this.other, this.user_uuid, "PAY", amount);
                break;
            case PAYSEND:
                MySQL.logTransaction(this.user_uuid, this.other, "PAY", amount);
                break;
            default:
                break;
        }
    }

    public void process() {
        switch(this.type) {
            case REWARD:
                MySQL.addToBalance(Bukkit.getPlayer(UUID.fromString(this.user_uuid)), this.amount);
                logTransaction();
                break;
            case CHARGE:
                MySQL.subtractFromBalance(Bukkit.getPlayer(UUID.fromString(this.user_uuid)), this.amount);
                logTransaction();
                break;
            case PAYRECIEVE:
                MySQL.subtractFromBalance(Bukkit.getPlayer(UUID.fromString(this.other)), this.amount);
                MySQL.addToBalance(Bukkit.getPlayer(UUID.fromString(this.user_uuid)), this.amount);
                logTransaction();
                break;
            case PAYSEND:
                MySQL.addToBalance(Bukkit.getPlayer(UUID.fromString(this.other)), this.amount);
                MySQL.subtractFromBalance(Bukkit.getPlayer(UUID.fromString(this.user_uuid)), this.amount);
                logTransaction();
                break;
            default:
                break;
        }
    }
}
