package cz.minelife;

import cz.minelife.commands.EndSession;
import cz.minelife.commands.ItemCmd;
import cz.minelife.commands.givelive.GiveLive;
import cz.minelife.commands.SetupCmd;
import cz.minelife.commands.StartSession;
import cz.minelife.items.Craftings;

import static cz.minelife.Main.main;

public class Commands {
    public Commands() {
        //SETUP
        main.getCommand("setupCmd").setExecutor(new SetupCmd());

        //STARTSESSION
        main.getCommand("startSession").setExecutor(new StartSession());

        //ENDSESSION
        main.getCommand("endSession").setExecutor(new EndSession());

        //GIVELIVE
        main.getCommand("givelive").setExecutor(new GiveLive());

        //ITEM
        main.getCommand("item").setExecutor(new ItemCmd());
        new Craftings();
    }
}
