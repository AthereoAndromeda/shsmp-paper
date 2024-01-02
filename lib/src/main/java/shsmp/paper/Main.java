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
import shsmp.paper.recipes.LightNecronomicon;
import shsmp.paper.recipes.Necronomicon;

import java.io.IOException;

public class Main extends JavaPlugin {
    public FileConfiguration config;
    public MyListener listener;

    public TeamsFile teamsFile;

    @Override
    public void onEnable() {
        this.config = getConfig();
        this.listener = new MyListener(this);
        configFileHandler();

        //Teams
        TeamsFile ded = new TeamsFile("ded.txt");

        try {
            ded.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.teamsFile = ded;

        // Adds the event handlers
        PluginManager bukkitPluginManager = Bukkit.getPluginManager();
        bukkitPluginManager.registerEvents(listener, this);

        // Add Recipes
        if (config.getBoolean("EnableLightGapple")) {
            Bukkit.addRecipe(new LightGapple().getRecipe());
        }

        if (config.getBoolean("LightNecronomicon")) {
            Bukkit.addRecipe(new LightNecronomicon().getRecipe());
        } else {
            Bukkit.addRecipe(new Necronomicon().getRecipe());
        }
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
        config.addDefault("EnableLightGapple", true);

        config.options().copyDefaults(true);
        saveConfig();
    }
}