package cz.minelife;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

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
