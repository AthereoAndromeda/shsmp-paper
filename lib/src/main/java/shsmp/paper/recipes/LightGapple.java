package shsmp.paper.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import shsmp.paper.Main;

/**
 * Light Gapple Reicpe
 */
public class LightGapple   {
    private final ShapedRecipe recipe;

    public LightGapple() {
        Main plugin = JavaPlugin.getPlugin(Main.class);

        ItemStack lightGapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta lightGappleMeta = lightGapple.getItemMeta();
        lightGappleMeta.setDisplayName("Light Golden Apple");
        lightGapple.setItemMeta(lightGappleMeta);

        // Recipe
        NamespacedKey key = new NamespacedKey(plugin, "light_golden_apple");
        ShapedRecipe lightGappleRecipe = new ShapedRecipe(key, lightGapple);

        // G = Gold ingot, A = Apple
        lightGappleRecipe.shape(
                " G ",
                "GAG",
                " G "
        );

        lightGappleRecipe.setIngredient('G', Material.GOLD_INGOT);
        lightGappleRecipe.setIngredient('A', Material.APPLE);

        this.recipe = lightGappleRecipe;
    }

    public ShapedRecipe getRecipe() {
        return this.recipe;
    }
}
