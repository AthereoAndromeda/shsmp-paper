package shsmp.paper.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Assign {
    public static boolean execute(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if (args[0].isBlank()) {
            sender.sendMessage("A player is required");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        assert player != null;
        sender.sendMessage("WWW" + player.getName());
        return true;
    }
}
