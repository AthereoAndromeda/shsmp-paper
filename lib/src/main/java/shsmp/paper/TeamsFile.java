package shsmp.paper;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/*
* Long term storage via file
*
*/
public class TeamsFile {
    private Main plugin;
    private String path;
    private String folderpath;
    private File file;

    public TeamsFile(Main plugin, String path, String folder) {
        this.plugin = JavaPlugin.getPlugin(Main.class);
        this.path = path;
        this.folderpath = folder;
        this.file = null;
    }

    public void init() {
        this.file = new File(plugin.getDataFolder(), this.path);

//        file
    }
}