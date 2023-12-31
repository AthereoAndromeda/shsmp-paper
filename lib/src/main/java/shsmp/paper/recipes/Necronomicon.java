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
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import shsmp.paper.Main;

/**
 * Necronomicon to Resurrect and Revive players
 */
public class Necronomicon {
    private final ShapedRecipe recipe;
    private final ItemStack item;

    public Necronomicon() {

        Main plugin = JavaPlugin.getPlugin(Main.class);
        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();

        BaseComponent[] firstPage = new ComponentBuilder("The Necronomicon")
                .create();

        bookMeta.spigot().addPage(firstPage);

        // List all players
        for (Player onlplayer : Bukkit.getOnlinePlayers()) {
            // TODO store time person died
            String content = "Time died to be set soon";
            String coloredContent = ChatColor.translateAlternateColorCodes('&', onlplayer.getDisplayName() + "\n\n" + content);

            // Only adds dead players
            if (onlplayer.isDead()) {
                BaseComponent[] page = new ComponentBuilder(coloredContent)
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/shsmp:revive " + onlplayer.getUniqueId()))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Revive this player?")))
                        .create();

                // add the page to the meta
                bookMeta.spigot().addPage(page);
            }

        }

        BaseComponent[] refreshPage = new ComponentBuilder("Refresh")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/shsmp:refresh"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Refresh the book")))
                .create();

        bookMeta.spigot().addPage(refreshPage);

        //set the title and author of this book
        bookMeta.setAuthor("SHSMP");
        bookMeta.setTitle("Necronomicon");
        bookMeta.setDisplayName("The Necronomicon");

        //update the ItemStack with this new meta
        writtenBook.setItemMeta(bookMeta);

        NamespacedKey key = new NamespacedKey(plugin, "necronomicon");
        ShapedRecipe recipe = new ShapedRecipe(key, writtenBook);

        // G = ench gapple, B = book
        if (plugin.getConfig().getBoolean("LightNecronomicon")) {
            recipe.shape(
                    " G ",
                    "GBG",
                    " G "
            );
        } else {
            recipe.shape(
                    "GGG",
                    "GBG",
                    "GGG"
            );
        }

        recipe.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
        recipe.setIngredient('B', Material.BOOK);

        this.item = writtenBook;
        this.recipe = recipe;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public ShapedRecipe getRecipe() {
        return this.recipe;
    }
}