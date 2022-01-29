package cz.minelife.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public enum ETeams {
    ONELIVE("oneLive", 1),
    TWOLIVES("twoLives", 2),
    THREELIVES("threeLives", 3),
    FOURLIVES("fourLives", 4),
    FIVELIVES("fiveLives", 5),
    SIXLIVES("sixLives", 6);

    private int intLives;
    private String teamName;
    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    ETeams(String teamName, int intLives) {
        this.teamName = teamName;
        this.intLives = intLives;
    }

    public Team getTeam() {
        return scoreboard.getTeam(teamName);
    }

    public void removePlayer(Player p) {
        this.getTeam().removeEntry(p.getDisplayName());
    }

    public void setTeam(Player p) {
        scoreboard.getTeam(teamName).addEntry(p.getDisplayName());
    }

    public int getIntLives() {
        return intLives;
    }

    public String getTeamName() {
        return teamName;
    }
}
