package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static org.bukkit.ChatColor.DARK_GREEN;

public class Players implements Listener {
    private JavaPlugin plugin;
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard scoreboard = manager.getMainScoreboard();
    private Yaml yaml;
    Team oneLive;
    Team twoLives;
    Team threeLives;
    Team fourLives;
    Team fiveLives;
    Team sixLives;

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

        oneLive.setPrefix(ChatColor.DARK_RED + "[1] ");
        twoLives.setPrefix(ChatColor.RED + "[2] ");
        threeLives.setPrefix(ChatColor.YELLOW + "[3] ");
        fourLives.setPrefix(ChatColor.GREEN + "[4] ");
        fiveLives.setPrefix(DARK_GREEN + "[5] ");
        sixLives.setPrefix(DARK_GREEN + "[6] ");

        oneLive.setColor(ChatColor.DARK_RED);
        twoLives.setColor(ChatColor.RED);
        threeLives.setColor(ChatColor.YELLOW);
        fourLives.setColor(ChatColor.GREEN);
        fiveLives.setColor(DARK_GREEN);
        sixLives.setColor(ChatColor.DARK_GREEN);

        this.addPlayersToTeams();
    }

    public void addPlayersToTeams() {
        for (Player p: Bukkit.getOnlinePlayers()) {
            int lives = yaml.getPlayerLives(p);

            try {
                scoreboard.getEntryTeam(p.getDisplayName()).removeEntry(p.getDisplayName());
            } catch(Exception e) {
                e.printStackTrace();
            }

            switch (lives) {
                case 1:
                    oneLive.addEntry(p.getDisplayName());
                case 2:
                    twoLives.addEntry(p.getDisplayName());
                case 3:
                    threeLives.addEntry(p.getDisplayName());
                case 4:
                    fourLives.addEntry(p.getDisplayName());
                case 5:
                    fiveLives.addEntry(p.getDisplayName());
                case 6:
                    sixLives.addEntry(p.getDisplayName());
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());

        playerTeam.removeEntry(p.getDisplayName());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

    }
}