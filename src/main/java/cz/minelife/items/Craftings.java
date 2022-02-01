package cz.minelife.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static cz.minelife.Main.main;
import static cz.minelife.items.Items.ONELIVE_STICK;

public class Craftings {
    private ItemStack item = ONELIVE_STICK.getItem();
    private ShapedRecipe recipe;

    public Craftings() {
        recipe = new ShapedRecipe(new NamespacedKey(main, "onelive_stick"), item);

        recipe.shape("GDG", "DSD", "GDG");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('G', Material.GOLD_NUGGET);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }
}
