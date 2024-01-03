package shsmp.paper.recipes;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import shsmp.paper.Main;

public class LightNecronomicon extends BaseNecronomicon {

    public ShapedRecipe getRecipe() {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        ItemStack necronomiconBook = getItem();

        NamespacedKey key = new NamespacedKey(plugin, "necronomicon");
        ShapedRecipe recipe = new ShapedRecipe(key, necronomiconBook);

        // G = ench gapple, B = book
        recipe.shape(
                " G ",
                "GBG",
                " G "
        );

        recipe.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
        recipe.setIngredient('B', Material.BOOK);

        return recipe;

    }
}
