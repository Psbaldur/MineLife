package cz.minelife.commands.givelive;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static cz.minelife.commands.givelive.Items.*;

public class GiveLiveGUI implements Listener {
    protected final Inventory inv;
    protected Yaml yaml;
    protected Player santa;
    protected Player receiver;
    protected HashMap<Integer, ItemStack> items = new HashMap<>();

    public GiveLiveGUI(Player santa, Player receiver, Yaml yaml) {
        this.santa = santa;
        this.receiver = receiver;
        this.yaml = yaml;

        inv = Bukkit.createInventory(null, 9, "§cGiveLive - Vyber možnost");

        this.addItems();
        this.openInventory();
    }

    public void addItems() {
        items.put(0, CANCEL.returnItem());
        items.put(3, ONE.returnItem());
        items.put(5, TWO.returnItem());
        items.put(8, CONFIRM.returnItem());

        inv.setItem(0, items.get(8));
        inv.setItem(3, items.get(8));
        inv.setItem(5, items.get(8));
        inv.setItem(8, items.get(8));
    }

    public void openInventory() {
        receiver.openInventory(inv);
    }
}
