package cz.minelife;

import cz.minelife.players.handlers.OnDeath;
import cz.minelife.players.handlers.OnPlayerChat;
import cz.minelife.players.handlers.OnPlayerJoin;

public class Events {
    public Events(Main main) {
        //onDeath Listener
        main.getServer().getPluginManager().registerEvents(new OnDeath(main), main);

        //onPlayerChat Listener
        main.getServer().getPluginManager().registerEvents(new OnPlayerChat(), main);

        //onPlayerJoin Listener
        main.getServer().getPluginManager().registerEvents(new OnPlayerJoin(main), main);
    }
}
