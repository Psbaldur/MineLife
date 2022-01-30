package cz.minelife.teams;

import cz.minelife.events.ChangeLivesEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static cz.minelife.teams.ETeams.*;

public class TeamUtil {
    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public static ETeams getPlayerTeam(Player p) {
        Team team = scoreboard.getEntryTeam(p.getDisplayName());
        if (team != null) {
            switch (team.getDisplayName()) {
                case "oneLive":
                    return ONELIVE;
                case "twoLives":
                    return TWOLIVES;
                case "threeLives":
                    return THREELIVES;
                case "fourLives":
                    return FOURLIVES;
                case "fiveLives":
                    return FIVELIVES;
                case "sixLives":
                    return SIXLIVES;
            }
        }
        return null;
    }

    public static ETeams getTeamByLiveCount(int liveCount) {
        switch(liveCount) {
            case 1:
                return ONELIVE;
            case 2:
                return TWOLIVES;
            case 3:
                return THREELIVES;
            case 4:
                return FOURLIVES;
            case 5:
                return FIVELIVES;
            case 6:
                return SIXLIVES;
        }
        return null;
    }

    public static void setPlayerLives(Player p, int liveCount, boolean isFirstTime) {
        ChangeLivesEvent changeLivesEvent;

        changeLivesEvent = new ChangeLivesEvent(p, liveCount, getPlayerLives(p), false);

        Bukkit.getPluginManager().callEvent(changeLivesEvent);

        if (!changeLivesEvent.isCancelled()) {
            ETeams team = getTeamByLiveCount(liveCount);
            if (liveCount != 0)
                team.setTeam(p);
            else
                team.removePlayer(p);
        }
    }

    public static int getPlayerLives(Player p) {
        if (getPlayerTeam(p) != null)
            return getPlayerTeam(p).getIntLives();
        else
            return 0;
    }

    public static void setupTeams() {
        Team oneLive;
        Team twoLives;
        Team threeLives;
        Team fourLives;
        Team fiveLives;
        Team sixLives;

        for (Team t: scoreboard.getTeams()) {
            t.unregister();
        }

        oneLive = scoreboard.registerNewTeam("oneLive");
        twoLives = scoreboard.registerNewTeam("twoLives");
        threeLives = scoreboard.registerNewTeam("threeLives");
        fourLives = scoreboard.registerNewTeam("fourLives");
        fiveLives = scoreboard.registerNewTeam("fiveLives");
        sixLives = scoreboard.registerNewTeam("sixLives");

        oneLive.setColor(ChatColor.DARK_RED);
        twoLives.setColor(ChatColor.RED);
        threeLives.setColor(ChatColor.GOLD);
        fourLives.setColor(ChatColor.GREEN);
        fiveLives.setColor(ChatColor.DARK_GREEN);
        sixLives.setColor(ChatColor.DARK_GREEN);
    }
}
