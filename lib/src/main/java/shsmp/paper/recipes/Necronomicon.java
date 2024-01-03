package shsmp.paper.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import shsmp.paper.Main;


/**
 * Necronomicon to Resurrect and Revive players
 */
public class Necronomicon extends BaseNecronomicon {

    public ShapedRecipe getRecipe() {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        ItemStack necronomiconBook = getItem();

        NamespacedKey key = new NamespacedKey(plugin, "necronomicon");
        ShapedRecipe recipe = new ShapedRecipe(key, necronomiconBook);

        // G = ench gapple, B = book
        recipe.shape(
                "GGG",
                "GBG",
                "GGG"
        );

        recipe.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
        recipe.setIngredient('B', Material.BOOK);

        return recipe;
    }
}