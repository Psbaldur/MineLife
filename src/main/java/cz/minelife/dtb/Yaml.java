package cz.minelife.dtb;

import cz.minelife.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Yaml {
    private Main plugin;
    private FileConfiguration config;

    public Yaml(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void setBoogeyman(Player p) {
        this.config.set("boogeyman.who", p.getDisplayName());
        this.config.set("boogeyman.isCured", false);
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

