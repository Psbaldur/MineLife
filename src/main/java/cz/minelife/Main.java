package cz.minelife;

import cz.minelife.commands.EndSession;
import cz.minelife.commands.SetupCmd;
import cz.minelife.players.Boogeyman;
import cz.minelife.players.Players;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        Players players = new Players(this);

        getCommand("setupCmd").setExecutor(new SetupCmd(this));
        getCommand("startSession").setExecutor(new Boogeyman(this));
        getCommand("endSession").setExecutor(new EndSession(this));
        getCommand("givelive").setExecutor(players);

        getServer().getPluginManager().registerEvents(players, this);
        getLogger().info("Plugin MineLife byl úspěšně zapnut");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin MineLife byl vypnut");
    }
}
