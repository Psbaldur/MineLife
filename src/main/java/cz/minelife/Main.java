package cz.minelife;

import cz.minelife.lifes.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getCommand("setupCmd").setExecutor(new SetupCmd(this));
        getCommand("startSession").setExecutor(new Boogeyman(this));
        getCommand("endSession").setExecutor(new EndSession(this));
        getCommand("givelife").setExecutor(new GiveLive(this));

        getServer().getPluginManager().registerEvents(new Players(this), this);
        getLogger().info("Plugin MineLife byl úspěšně zapnut");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin MineLife byl vypnut");
    }
}
