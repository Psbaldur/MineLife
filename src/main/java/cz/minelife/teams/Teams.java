package cz.minelife.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public enum Teams {
    ONELIVE("oneLive"),
    TWOLIVES("twoLives"),
    THREELIVES("threeLives"),
    FOURLIVES("fourLives"),
    FIVELIVES("fiveLives"),
    SIXLIVES("sixLives");

    private String lives;
    private final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    Teams(String lives) {
        this.lives = lives;
    }

    public Team getTeam() {
        return scoreboard.getTeam(lives);
    }

    public void setTeam(Player p) {
        scoreboard.getTeam(lives).addEntry(p.getDisplayName());
    }
}
