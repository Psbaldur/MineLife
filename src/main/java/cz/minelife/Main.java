package cz.minelife;

import cz.minelife.lifes.Boogeyman;
import cz.minelife.lifes.EndSession;
import cz.minelife.lifes.Players;
import cz.minelife.lifes.SetupCmd;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getCommand("setupCmd").setExecutor(new SetupCmd(this));
        getCommand("startSession").setExecutor(new Boogeyman(this));
        getCommand("endSession").setExecutor(new EndSession(this));

        getServer().getPluginManager().registerEvents(new Players(this), this);
        getLogger().info("Plugin MineLife byl úspěšně zapnut");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin MineLife byl vypnut");
    }
}
