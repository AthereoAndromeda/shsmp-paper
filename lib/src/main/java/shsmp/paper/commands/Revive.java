package shsmp.paper.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import shsmp.paper.DiscordWebhook;
import shsmp.paper.Main;
import shsmp.paper.recipes.UsedNecronomicon;

import java.io.IOException;

public class Revive {

    public static boolean execute(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        Main plugin = JavaPlugin.getPlugin(Main.class);

        if (sender instanceof Player) {
            // Gets player
            Player revivedPlayer = Bukkit.getPlayer(args[0]);
            Player revivingPlayer = (Player) sender;

            // Teleport revivedPlayer to revivingPlayer
            String tpCommand = "teleport " + revivedPlayer.getName() + " " + revivingPlayer.getName();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), tpCommand);

            // Changes gamemode which "revives" the player. Also sets them to max health and
            // hunger
            revivedPlayer.setGameMode(GameMode.SURVIVAL);
            revivedPlayer.setHealth(revivedPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            revivedPlayer.setFoodLevel(20);

            // Replace Necronomicon with Used Necronomicon
            revivingPlayer.getInventory()
                    .setItemInMainHand(new UsedNecronomicon(revivedPlayer, revivingPlayer).getItem());

            // Broadcast Message that someone has been revived
            String revivedMessage = ChatColor.translateAlternateColorCodes('&',
                    "&b" + revivedPlayer.getDisplayName() + " was revived by " + revivingPlayer.getDisplayName());
            Bukkit.broadcastMessage(revivedMessage);

            if (plugin.getConfig().getBoolean("EnableDiscordWebhook")) {
                DiscordWebhook webhook = new DiscordWebhook(plugin.getConfig().getString("DiscordWebhook"));
                DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
                        .setTitle(revivedPlayer.getDisplayName() + " Has been Resurrected!")
                        .setDescription("Revived by " + revivingPlayer.getDisplayName());

                webhook.addEmbed(embed);

                // Send Discord Webhook that someone has been revived
                try {
                    webhook.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return true;
        } else {
            return false;
        }


    }
}
