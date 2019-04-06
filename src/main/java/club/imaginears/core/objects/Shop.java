package club.imaginears.core.objects;

import club.imaginears.core.utils.ShopSigns;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Shop {

    private Location location;
    private String name;
    private String store;
    private String uuid;
    private Float price;
    private ItemStack[] items;

    public Shop(Location location, String name, String store, Float price, ItemStack[] items, String uuid) {
        this.location = location;
        this.name = name;
        this.store = store;
        this.price = price;
        this.items = items;
        this.uuid = uuid;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getStore() {
        return store;
    }

    public String getUUID() {
        return uuid;
    }

    public Float getPrice() {
        return price;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public void deleteShop() {
        ShopSigns.delShop(uuid);
    }

}
