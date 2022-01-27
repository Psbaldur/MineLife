package cz.minelife.commands.givelive;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static cz.minelife.utils.Items.createItem;

public enum ItemsGUI {
    CANCEL (Material.RED_WOOL, 1, "§cZrušit", "§7Kliknutím na toto zrušíš dávání životu"),
    ONE (Material.GRAY_WOOL, 1, "§l1", "§7Kliknutím na toto dáš danému hráči jeden život"),
    TWO (Material.GRAY_WOOL, 2, "§l2", "§7Kliknutím na toto dáš danému hráči dva životy"),
    CONFIRM (Material.GREEN_WOOL, 1, "§aPotvrdit", "§7Kliknutím na toto potvrdíš předání životu");

    private final Material material;
    private final int amount;
    private final String name;
    private final String lore;

    ItemsGUI(Material material, int amount, String name, String lore) {
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.lore = lore;
    }

    public ItemStack returnItem() {
        return createItem(material, amount, name, lore);
    }
}
