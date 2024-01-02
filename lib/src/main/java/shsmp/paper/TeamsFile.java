package shsmp.paper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Long term storage via file
*
*/
public class TeamsFile {
    private final Main plugin;
    private final Logger logger;
    private final File file;

    public TeamsFile(String fileName) {
        this.plugin = JavaPlugin.getPlugin(Main.class);
        this.logger = plugin.getLogger();
        this.file = new File(plugin.getDataFolder(), fileName);
    }

    public void init() throws IOException {
        if (file.createNewFile()) {
            logger.log(Level.INFO, "Team File created");
        } else {
            logger.log(Level.INFO, "Team File already created");
        }

        logger.log(Level.INFO, "Team File Init Complete");
    }

    public void addPlayer(Player player) throws IOException {
        String playerName = player.getName();
        ArrayList<String> playerList = new ArrayList<>();

        Scanner scanner = new Scanner(file);

        // Read file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            logger.log(Level.INFO, line);
            playerList.add(line);
        }
        scanner.close();

        // Write player name to file
        FileWriter writer = new FileWriter(file, true);
        if (playerList.contains(playerName)) {
            logger.log(Level.INFO, "ALREADY CONTAINED");
        } else {
            writer.append(playerName);
            logger.log(Level.INFO, "ADDED TO FILE");
        }
        writer.close();
    }

    public void removePlayer(Player player) throws IOException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> playerList = new ArrayList<>();

        // Read file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            logger.log(Level.INFO, line);
            playerList.add(line);
        }
        scanner.close();

        playerList.removeIf(playerName -> playerName.equals(player.getName()));

        FileWriter writer = new FileWriter(file);

        for (String playerName: playerList) {
            writer.write(playerName);
        }

        writer.close();
    }

    public ArrayList<Player> getPlayers() throws FileNotFoundException {
        ArrayList<Player> playerList = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        // Read file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            logger.log(Level.INFO, line);

            Player player = Bukkit.getPlayer(line);
            playerList.add(player);
        }
        scanner.close();

        return playerList;
    }
}