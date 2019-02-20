package club.imaginears.core.objects;

import net.md_5.bungee.api.ChatColor;

public enum Rank {
    DEVELOPER("Developer", "developer", "&f[&5Developer&f]", "&f", 12),
    MANAGER("Manager", "manager", "&f[&aManager&f]", "&f", 11),
    COORDINATOR("Coordinator", "coordinator", "&f[&cCoordinator&f]", "&f", 10),
    IMAGINEERS("Imagineers", "imagineers", "&f[&3Cast Member&f]", "&f", 9),
    CASTMEMBER("Castmember", "castmember", "&f[&3Cast Member&f]", "&f", 9),
    CHARACTER("Character", "character", "&f[&6Character&f]", "&6", 8),
    PHOTOPASS("Photopass", "photopass", "&f[&3Photopass&f]", "&f", 7),
    SECURITY("Security", "security", "&f[&4Security&f]", "&f", 6),
    SPECIALGUEST("Specialguest", "specialguest", "&0[&4Special Guest&0]", "&f", 5),
    PLATINUMPLUS("Platinum+", "platinum+", "&f[&6Platinum+&f]", "&f", 4),
    PLATINUM("Platinum", "platinum", "&f[&6Platinum&f]", "&f", 3),
    GOLD("Gold", "gold", "&f[&6Gold&f]", "&f", 2),
    SILVER("Silver", "silver", "&f[&6Silver&f]", "&f", 1),
    GUEST("Guest", "default", "&f[&eGuest&f]", "&f", 0),
    ERROR("Error", "error", "error", "error", -1);

    public String name;
    public String actual;
    public String prefix;
    public String chatColor;
    public int rankLadder;

    Rank(String name, String actual, String prefix, String chatColor, int rankLadder) {
        this.name = name;
        this.actual = actual;
        this.prefix = prefix;
        this.chatColor = chatColor;
        this.rankLadder = rankLadder;
    }

    public static Rank fromString(String string) {
        String rankName = string.toLowerCase();
        switch (rankName) {
            case "developer":
                return DEVELOPER;
            case "manager":
                return MANAGER;
            case "coordinator":
                return COORDINATOR;
            case "imagineers":
                return IMAGINEERS;
            case "castmember":
                return CASTMEMBER;
            case "character":
                return CHARACTER;
            case "photopass":
                return PHOTOPASS;
            case "security":
                return SECURITY;
            case "specialguest":
                return SPECIALGUEST;
            case "platinum+":
                return PLATINUMPLUS;
            case "platinum":
                return PLATINUM;
            case "gold":
                return GOLD;
            case "silver":
                return SILVER;
            case "default":
                return GUEST;
            default:
                return ERROR;
        }
    }

    public int getRankLadder() {
        return rankLadder;
    }

    public String getName() {
        return name;
    }

    public String getActualName() {
        return actual;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getChatChat() {
        return chatColor;
    }

}