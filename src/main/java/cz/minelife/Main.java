package cz.minelife;

import cz.minelife.commands.givelive.GiveLiveGUI;
import cz.minelife.players.Players;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        new Commands(this);
        new Events(this);
        getLogger().info("Plugin MineLife byl úspěšně zapnut");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin MineLife byl vypnut");
    }
}
