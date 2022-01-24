package cz.minelife.players.handlers;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import static cz.minelife.teams.Teams.*;

public class OnPlayerJoin {
    private Yaml yaml;
    private JavaPlugin plugin;
    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public OnPlayerJoin(JavaPlugin plugin) {
        this.plugin = plugin;
        yaml = new Yaml(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (scoreboard.getEntryTeam(p.getDisplayName()) == null) {
            int playerLives = yaml.getPlayerLives(p);

            switch (playerLives) {
                case 1:
                    ONELIVE.getTeam().addEntry(p.getDisplayName());
                    break;
                case 2:
                    TWOLIVES.getTeam().addEntry(p.getDisplayName());
                    break;
                case 3:
                    THREELIVES.getTeam().addEntry(p.getDisplayName());
                    break;
                case 4:
                    FOURLIVES.getTeam().addEntry(p.getDisplayName());
                    break;
                case 5:
                    FIVELIVES.getTeam().addEntry(p.getDisplayName());
                    break;
                case 6:
                    SIXLIVES.getTeam().addEntry(p.getDisplayName());
                    break;
            }
        }
    }
}
