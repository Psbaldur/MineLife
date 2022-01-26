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

    public GiveLiveGUI(Player santa, Player receiver, Yaml yaml) {
        this.santa = santa;
        this.receiver = receiver;
        this.yaml = yaml;

        inv = Bukkit.createInventory(null, 9, "§cGiveLive - Vyber možnost");

        this.addItems();
        this.openInventory();
    }

    public void addItems() {
        inv.setItem(0, CANCEL.returnItem());
        inv.setItem(3, ONE.returnItem());
        inv.setItem(5, TWO.returnItem());
        inv.setItem(8, CONFIRM.returnItem());
    }

    public void openInventory() {
        if (santa == receiver || receiver == null) {
            santa.sendMessage("§cError");
            return;
        }
        santa.openInventory(inv);
    }
}
