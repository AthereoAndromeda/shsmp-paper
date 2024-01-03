package shsmp.paper.recipes;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import shsmp.paper.Main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BaseNecronomicon {

    public ItemStack getItem() {
        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta =  getBookMeta();
        writtenBook.setItemMeta(bookMeta);
        return writtenBook;
    }

    public BookMeta getBookMeta() {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
        BaseComponent[] firstPage = new ComponentBuilder("The Necronomicon")
                .create();

        bookMeta.spigot().addPage(firstPage);

        ArrayList<Player> deadPlayers = null;

        try {
            deadPlayers = plugin.teamsFile.getPlayers();
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Player player : deadPlayers) {
            String playerName = player.getName();
            String content = "Time died to be set soon";
            String coloredContent = ChatColor.translateAlternateColorCodes('&', playerName + "\n\n" + content);

            BaseComponent[] page = new ComponentBuilder(coloredContent)
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/shsmp:revive " + playerName))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Revive this player?")))
                    .create();

            // add the page to the meta
            bookMeta.spigot().addPage(page);
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

        return bookMeta;
    }
}
