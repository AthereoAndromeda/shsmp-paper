package shsmp.paper;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import shsmp.paper.commands.Assign;
import shsmp.paper.commands.Fly;
import shsmp.paper.commands.Refresh;
import shsmp.paper.commands.Revive;
import shsmp.paper.recipes.LightGapple;
import shsmp.paper.recipes.Necronomicon;

public class Main extends JavaPlugin {
    public FileConfiguration config;
    public MyListener listener;

    @Override
    public void onEnable() {
        this.config = getConfig();
        this.listener = new MyListener(this);
        configFileHandler();

        // Adds the event handlers
        PluginManager bukkitPluginManager = Bukkit.getPluginManager();
        bukkitPluginManager.registerEvents(listener, this);

        // Add Recipes
        Bukkit.addRecipe(new LightGapple().getRecipe());
        Bukkit.addRecipe(new Necronomicon().getRecipe());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {

        // Revives dead player
        if (label.equalsIgnoreCase("shsmp:revive")) {
            return Revive.execute(sender, command, label, args);
        }

        // Refreshes and updates book
        if (label.equalsIgnoreCase("shsmp:refresh")) {
            return Refresh.execute(sender, command, label, args);
        }

        if (label.equalsIgnoreCase("shsmp:assign")) {
            return Assign.execute(sender, command, label, args);
        }

        if (label.equalsIgnoreCase("shsmp:fly")) {
            return Fly.execute(sender, command, label, args);
        }

        return  false;
    }

    /**
     * Handles The config.yml setup
     */
    private void configFileHandler() {
        config.addDefault("EnableDiscordWebhook", false);
        config.addDefault("DiscordWebhookLink", "Insert Webhook here");
        config.addDefault("LightNecronomicon", false);

        config.options().copyDefaults(true);
        saveConfig();
    }
}