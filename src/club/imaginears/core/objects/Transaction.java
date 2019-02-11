package club.imaginears.core.objects;

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
}
