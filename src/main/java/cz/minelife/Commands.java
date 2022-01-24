package cz.minelife;

import cz.minelife.commands.EndSession;
import cz.minelife.commands.SetupCmd;
import cz.minelife.players.Boogeyman;

public class Commands {
    public Commands(Main main) {
        //SETUPCMD
        main.getCommand("setupCmd").setExecutor(new SetupCmd(main));

        //STARTSESSION
        main.getCommand("startSession").setExecutor(new Boogeyman(main));

        //ENDSESSION
        main.getCommand("endSession").setExecutor(new EndSession(main));

        //GIVELIVE
        main.getCommand("givelive").setExecutor(players);
    }
}