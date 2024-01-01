package shsmp.paper.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class Fly {
    public static boolean execute(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if (args[0].isBlank()) {
            sender.sendMessage("A player is required");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        assert player != null;

        player.setFlying(true);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 3));
        sender.sendMessage("He can fly now. also invisible lol");

        return true;
    }
}
