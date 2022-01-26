package cz.minelife.players.handlers;

import cz.minelife.Main;
import cz.minelife.teams.ETeams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static cz.minelife.actionbar.ActionBar.livesActionBar;
import static cz.minelife.teams.TeamUtil.getPlayerTeam;

public class OnPlayerJoin implements Listener {
    private Main plugin;

    public OnPlayerJoin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ETeams team = getPlayerTeam(p);

        switch (team) {
            case ONELIVE:
                livesActionBar.put(p, "§41 ŽIVOT");
                break;
            case TWOLIVES:
                livesActionBar.put(p, "§c2 ŽIVOTY");
                break;
            case THREELIVES:
                livesActionBar.put(p, "§63 ŽIVOTY");
                break;
            case FOURLIVES:
                livesActionBar.put(p, "§a4 ŽIVOTY");
                break;
            case FIVELIVES:
                livesActionBar.put(p, "§25 ŽIVOTŮ");
                break;
            case SIXLIVES:
                livesActionBar.put(p, "§26 ŽIVOTŮ");
                break;
            default:
                livesActionBar.put(p, "§cNULL");
        }
    }
}
