package cz.minelife.teams;

import cz.minelife.events.ChangeLivesEvent;
import org.bukkit.Bukkit;
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

    public static void setPlayerLives(Player p, int liveCount) {
        ChangeLivesEvent changeLivesEvent = new ChangeLivesEvent(p, liveCount);

        Bukkit.getPluginManager().callEvent(changeLivesEvent);

        if (!changeLivesEvent.isCancelled()) {
            switch (liveCount) {
                case 1:
                    ONELIVE.setTeam(p);
                case 2:
                    TWOLIVES.setTeam(p);
                case 3:
                    THREELIVES.setTeam(p);
                case 4:
                    FOURLIVES.setTeam(p);
                case 5:
                    FIVELIVES.setTeam(p);
                case 6:
                    SIXLIVES.setTeam(p);
            }
        }
    }

    public static int getPlayerLives(Player p) {
        return getPlayerTeam(p).getIntLives();
    }
}
