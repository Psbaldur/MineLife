package cz.minelife.boss;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static cz.minelife.Main.main;
import static cz.minelife.items.Items.ONELIVE_STICK;
import static cz.minelife.items.Items.TWOLIVES_STICK;

public class BossLootTable implements LootTable {
    private Collection<ItemStack> items = new ArrayList<ItemStack>();
    private final NamespacedKey key = new NamespacedKey(main, "zombie_loot");

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        if (random.nextBoolean()) {
            items.add(ONELIVE_STICK.getItem());
        } else {
            items.add(TWOLIVES_STICK.getItem());
        }
        return items;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {

    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
