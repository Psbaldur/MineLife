package cz.minelife.players.handlers;

import cz.minelife.Main;
import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import static cz.minelife.teams.ETeams.*;

public class OnPlayerJoin implements Listener {
    private Yaml yaml;
    private Main plugin;
    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public OnPlayerJoin(Main plugin) {
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
