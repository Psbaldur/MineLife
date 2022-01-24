package cz.minelife.dtb;

import org.bukkit.Bukkit;
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
        this.config.set("players." + p.getUniqueId() + ".lives", lives);
        this.reloadConfig();
    }

    public int getPlayerLives(Player p) {
        return this.config.getInt("players." + p.getUniqueId() + ".lives");
    }

    public void setBoogeyman(Player p) {
        this.config.set("boogeyman.who", p.getDisplayName());
        this.config.set("boogeyman.isCured", false);
        this.reloadConfig();
    }

    public void setIfIsBoogeymanCured(Boolean bool) {
        this.config.set("boogeyman.isCured", bool);
    }

    public String getBoogeyman() {
        String boogeyman = (String) this.config.get("boogeyman.who");
        return boogeyman;
    }

    public Boolean getIfIsBoogeyCured() {
        Boolean boogeyman = this.config.getBoolean("boogeyman.isCured");
        return boogeyman;
    }
}

