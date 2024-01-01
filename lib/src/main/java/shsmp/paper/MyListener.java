package shsmp.paper;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerLoadEvent;

import shsmp.paper.DiscordWebhook.EmbedObject;
import shsmp.paper.recipes.Necronomicon;

import java.util.logging.Level;

public class MyListener implements Listener {
    private Main plugin;

    public MyListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

    }

    @EventHandler
    public void onCraft(CraftItemEvent event) throws Exception {
        if (event.isCancelled() || !(event.getWhoClicked() instanceof Player)) {
            return;
        }

        String eventRecipeName = event.getRecipe().getResult().getItemMeta().getAsString();
        String NecroName = new Necronomicon().getItem().getItemMeta().getAsString();

        // Going to make it check for lore instead soon, because it is possible
        // someone just changes the name using Anvil and get
        if (NecroName.equals(eventRecipeName)) {
            Player player = (Player) event.getWhoClicked();
            String rawMessage = "&3" + player.getName() + " &rhas crafted a &l&8Necronomicon.&r";
            String msg = ChatColor.translateAlternateColorCodes('&', rawMessage);

            Component c = Component.text(msg);
            Bukkit.broadcast(c);
            sendWebhook(event);
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        event.setDeathMessage(ChatColor.RED + "You are now dead, sad.");

        Player player = event.getEntity();

        // Player killer = player.getKiller();

//        player.sendMessage("You have been assigned to Dead team");


        Bukkit.broadcastMessage("Big oof. " + player.getDisplayName() + " has died");
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        plugin.getLogger().log(Level.INFO, "Respawned: " + event.getPlayer().getName());
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
//        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "team add SHSMP.Alive \"Alive Players\"");
//        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "team add SHSMP.Dead \"Dead Players\"");
//        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "team modify SHSMP.Alive color dark_aqua");
//        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "team modify SHSMP.Dead color dark_red");
    }

    /**
     * Sends webhook when a necronomicon has been crafted.
     *
     * @param event
     * @throws Exception
     */
    private void sendWebhook(CraftItemEvent event) throws Exception {
        Player player = (Player) event.getWhoClicked();

        boolean isWebhookEnabled = plugin.getConfig().getBoolean("EnableDiscordWebhook");

        if (isWebhookEnabled) {
            DiscordWebhook webhook = new DiscordWebhook(plugin.getConfig().getString("DiscordWebhook"));
            EmbedObject embed = new EmbedObject().setTitle(player.getName() + " has Crafted a Necronomicon!")
                    .setDescription("A player can now be revived!");

            webhook.addEmbed(embed);
            webhook.execute();
        }
    }
}
