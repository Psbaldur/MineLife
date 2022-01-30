package cz.minelife.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public enum Items {
    ONELIVE_STICK (Material.STICK, 1234567, Enchantment.WATER_WORKER, "§aKlacek Mistra stvořitele", "§7Svádí tě to k mávnutí s tím ve vzduchu"),
    TWOLIVES_STICK (Material.BLAZE_ROD, 7654321, Enchantment.WATER_WORKER, "§aHůlka života", "§7Je z toho cítit hodně energie"),
    BREAK_CURSE (Material.SUGAR, 1254557, Enchantment.WATER_WORKER, "§aČarodějný kámen mocné Babice", "§7Jakmile ho držíš v ruce, máš takový divný pocit síly.", "§7Bouchnutím jiného hráče ho zbavíš prokletí!");

    private ItemStack item;

    Items(Material material, int customModelData, Enchantment enchant, String name, String... lore) {
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(enchant, 60, true);
        meta.setCustomModelData(customModelData);
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return item.getItemMeta().getDisplayName();
    }
}
