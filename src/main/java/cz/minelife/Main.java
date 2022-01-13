package cz.minelife;

import cz.minelife.lifes.Players;
import cz.minelife.lifes.SetupCmd;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        this.getCommand("setupCmd").setExecutor(new SetupCmd(this));
        getServer().getPluginManager().registerEvents(new Players(this), this);
        getLogger().info("Plugin MineLife byl úspěšně zapnut");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin MineLife byl vypnut");
    }
}
