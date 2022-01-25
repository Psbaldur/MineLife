package cz.minelife;

import cz.minelife.commands.givelive.OnInventory;
import cz.minelife.players.handlers.OnPlayerDeath;
import cz.minelife.players.handlers.OnPlayerChat;

public class Events {
    public Events(Main main) {
        //onDeath Listener
        main.getServer().getPluginManager().registerEvents(new OnPlayerDeath(main), main);

        //onPlayerChat Listener
        main.getServer().getPluginManager().registerEvents(new OnPlayerChat(), main);
        
        //onInventoryClick and onInventoryDrag
        main.getServer().getPluginManager().registerEvents(new OnInventory(main), main);
    }
}
