package cz.minelife.commands.givelive;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static cz.minelife.commands.givelive.ItemsGUI.*;

public class GiveLiveGUI{
    protected final Inventory inv;
    protected Player santa;
    protected Player receiver;

    public GiveLiveGUI(Player santa, Player receiver) {
        this.santa = santa;
        this.receiver = receiver;

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

        santa = null;
        receiver = null;
    }
}
