package cz.minelife.teams;

import org.apache.commons.lang.ObjectUtils;
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
                default:
                    return null;
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
}
