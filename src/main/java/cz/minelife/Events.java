package cz.minelife;

import cz.minelife.actionbar.ActionBar;
import cz.minelife.actionbar.OnChangeLives;
import cz.minelife.commands.givelive.OnInventory;
import cz.minelife.items.handlers.OnPlayerInteract;
import cz.minelife.players.handlers.OnPlayerDeath;
import cz.minelife.players.handlers.OnPlayerChat;
import cz.minelife.players.handlers.OnPlayerJoin;

public class Events {
    public Events(Main main) {
        //onDeath Listener
        main.getServer().getPluginManager().registerEvents(new OnPlayerDeath(main), main);

        //onPlayerChat Listener
        main.getServer().getPluginManager().registerEvents(new OnPlayerChat(), main);
        
        //onInventoryClick and onInventoryDrag
        main.getServer().getPluginManager().registerEvents(new OnInventory(), main);

        //ActionBar
        new ActionBar(main);

        //onPlayerJoin
        main.getServer().getPluginManager().registerEvents(new OnPlayerJoin(main), main);

        //onChangeLives
        main.getServer().getPluginManager().registerEvents(new OnChangeLives(), main);

        //onPlayerInteract
        main.getServer().getPluginManager().registerEvents(new OnPlayerInteract(), main);
    }
}
