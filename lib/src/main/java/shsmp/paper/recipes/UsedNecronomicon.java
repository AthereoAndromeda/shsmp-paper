package shsmp.paper.recipes;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a used or deactivated Necronomicon
 */
public class UsedNecronomicon {
    private ItemStack item;

    public UsedNecronomicon(Player revivedPlayer, Player revivingPlayer) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

        String mainPageContent = revivedPlayer.getDisplayName() + " has been revived by " + revivingPlayer.getDisplayName() +
                "\n\nRevived on " + dateFormatter.format(now);

        BaseComponent[] mainPage = new ComponentBuilder(mainPageContent)
                .create();

        bookMeta.spigot().addPage(mainPage);

        //set the title and author of this book
        bookMeta.setAuthor("SHSMP");
        bookMeta.setTitle("Used Necronomicon");

        //update the ItemStack with this new meta
        book.setItemMeta(bookMeta);

        this.item = book;
    }

    public ItemStack getItem() {
        return this.item;
    }
}
