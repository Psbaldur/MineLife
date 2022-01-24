package cz.minelife;

import cz.minelife.players.onDeath;

public class Events {
    public Events(Main main) {
        //onDeath Listener
        main.getServer().getPluginManager().registerEvents(new onDeath(), main);
    }
}
