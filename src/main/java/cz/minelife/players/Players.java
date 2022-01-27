package cz.minelife.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

import static cz.minelife.teams.TeamUtil.setPlayerLives;


public class Players {
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard scoreboard = manager.getMainScoreboard();
    private HashMap<Player, Integer> lives = new HashMap<>();
    private Team oneLive;
    private Team twoLives;
    private Team threeLives;
    private Team fourLives;
    private Team fiveLives;
    private Team sixLives;

    public Players(HashMap<Player, Integer> lives) {
        this.lives = lives;
    }

    public void setup() {
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

        this.addPlayersToTeams();
    }

    public void addPlayersToTeams() {
        for (Player p: Bukkit.getOnlinePlayers()) {
            setPlayerLives(p, lives.get(p));
        }
    }
}