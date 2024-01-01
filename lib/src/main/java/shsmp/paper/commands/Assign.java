package shsmp.paper.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import shsmp.paper.Main;

import java.io.IOException;

public class Assign {
    public static boolean execute(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if (args[0].isBlank()) {
            sender.sendMessage("A player is required");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        assert player != null;
        sender.sendMessage("WWW" + player.getName());

        Main plugin = JavaPlugin.getPlugin(Main.class);

        try {
            plugin.teamsFile.addPlayer(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
