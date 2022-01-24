package cz.minelife;

import cz.minelife.commands.EndSession;
import cz.minelife.commands.GiveLive;
import cz.minelife.commands.SetupCmd;
import cz.minelife.commands.StartSession;
import cz.minelife.players.Boogeyman;

public class Commands {
    public Commands(Main main) {
        //SETUP
        main.getCommand("setupCmd").setExecutor(new SetupCmd(main));

        //STARTSESSION
        main.getCommand("startSession").setExecutor(new StartSession(main));

        //ENDSESSION
        main.getCommand("endSession").setExecutor(new EndSession(main));

        //GIVELIVE
        main.getCommand("givelive").setExecutor(new GiveLive(main));
    }
}
