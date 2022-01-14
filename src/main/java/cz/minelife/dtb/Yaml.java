package cz.minelife.dtb;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Yaml {
    private JavaPlugin plugin;
    private FileConfiguration config;
    public Yaml(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    private void reloadConfig() {
        this.plugin.saveConfig();
        this.config = plugin.getConfig();
    }
    public void setPlayerLives(Player p, int lives) {
        this.config.set("players." + p.getDisplayName() + ".lives", lives);
        this.reloadConfig();
    }
    public int getPlayerLives(Player p) {
        return this.config.getInt("players." + p.getDisplayName() + ".lives");
    }
}

