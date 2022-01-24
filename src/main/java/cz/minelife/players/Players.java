package cz.minelife.players;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static cz.minelife.teams.ETeams.*;


public class Players {
    private JavaPlugin plugin;
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard scoreboard = manager.getMainScoreboard();
    private Yaml yaml;
    private Team oneLive;
    private Team twoLives;
    private Team threeLives;
    private Team fourLives;
    private Team fiveLives;
    private Team sixLives;

    public Players(JavaPlugin plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(this.plugin);
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
            int lives = yaml.getPlayerLives(p);
            Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());

            if (playerTeam != null)
                playerTeam.removeEntry(p.getDisplayName());

            switch (lives) {
                case 1:
                    ONELIVE.setTeam(p);
                    break;
                case 2:
                    TWOLIVES.setTeam(p);
                    break;
                case 3:
                    THREELIVES.setTeam(p);
                    break;
                case 4:
                    FOURLIVES.setTeam(p);
                    break;
                case 5:
                    FIVELIVES.setTeam(p);
                    break;
                case 6:
                    SIXLIVES.setTeam(p);
                    break;
            }
        }
    }
}