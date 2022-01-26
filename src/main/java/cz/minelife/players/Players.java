package cz.minelife.players;

import cz.minelife.Main;
import cz.minelife.dtb.Yaml;
import cz.minelife.teams.ETeams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

import static cz.minelife.actionbar.ActionBar.livesActionBar;
import static cz.minelife.teams.ETeams.*;
import static cz.minelife.teams.TeamUtil.getPlayerTeam;


public class Players {
    private Main plugin;
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard scoreboard = manager.getMainScoreboard();
    private HashMap<Player, Integer> lives = new HashMap<>();
    private Team oneLive;
    private Team twoLives;
    private Team threeLives;
    private Team fourLives;
    private Team fiveLives;
    private Team sixLives;

    public Players(Main plugin, HashMap<Player, Integer> lives) {
        this.plugin = plugin;
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
            ETeams playerTeam = getPlayerTeam(p);

            if (playerTeam != null)
                playerTeam.removePlayer(p);

            switch (lives.get(p)) {
                case 1:
                    ONELIVE.setTeam(p);
                    livesActionBar.put(p, "§41 ŽIVOT");
                    break;
                case 2:
                    TWOLIVES.setTeam(p);
                    livesActionBar.put(p, "§c2 ŽIVOTY");
                    break;
                case 3:
                    THREELIVES.setTeam(p);
                    livesActionBar.put(p, "§63 ŽIVOTY");
                    break;
                case 4:
                    FOURLIVES.setTeam(p);
                    livesActionBar.put(p, "§a4 ŽIVOTY");
                    break;
                case 5:
                    FIVELIVES.setTeam(p);
                    livesActionBar.put(p, "§25 ŽIVOTŮ");
                    break;
                case 6:
                    SIXLIVES.setTeam(p);
                    livesActionBar.put(p, "§26 ŽIVOTŮ");
                    break;
            }
        }
    }
}