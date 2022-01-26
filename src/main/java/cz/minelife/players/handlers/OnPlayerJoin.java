package cz.minelife.players.handlers;

import cz.minelife.teams.ETeams;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static cz.minelife.actionbar.ActionBar.livesActionBar;
import static cz.minelife.teams.ETeams.*;
import static cz.minelife.teams.TeamUtil.getPlayerTeam;

public class OnPlayerJoin implements Listener {
    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ETeams team = getPlayerTeam(p);

        switch (team) {
            case ONELIVE:
                livesActionBar.putIfAbsent(p, "§41 ŽIVOT");
                break;
            case TWOLIVES:
                livesActionBar.putIfAbsent(p, "§c2 ŽIVOTY");
                break;
            case THREELIVES:
                livesActionBar.putIfAbsent(p, "§63 ŽIVOTY");
                break;
            case FOURLIVES:
                livesActionBar.putIfAbsent(p, "§a4 ŽIVOTY");
                break;
            case FIVELIVES:
                livesActionBar.putIfAbsent(p, "§25 ŽIVOTŮ");
                break;
            case SIXLIVES:
                livesActionBar.putIfAbsent(p, "§26 ŽIVOTŮ");
                break;
            default:
                livesActionBar.putIfAbsent(p, "§cNULL");
        }
    }
}
