package shsmp.paper;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
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
        logger.log(Level.INFO, "tryna init");

        if (file.createNewFile()) {
            plugin.getLogger().log(Level.INFO, "created");
        } else {
            plugin.getLogger().log(Level.INFO, "already created");
        }

        plugin.getLogger().log(Level.INFO, "File Init Complete");
    }
}