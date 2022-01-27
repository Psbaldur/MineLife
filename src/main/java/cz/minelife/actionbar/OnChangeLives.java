package cz.minelife.actionbar;

import cz.minelife.events.ChangeLivesEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static cz.minelife.actionbar.ActionBar.livesActionBar;

public class OnChangeLives implements Listener {
    @EventHandler
    public void onChangeLives(ChangeLivesEvent event) {
        Player p = event.getPlayer();
        switch (event.getNewLives()) {
            case 1:
                livesActionBar.put(p, "§41 ŽIVOT");
                break;
            case 2:
                livesActionBar.put(p, "§c2 ŽIVOTY");
                break;
            case 3:
                livesActionBar.put(p, "§63 ŽIVOTY");
                break;
            case 4:
                livesActionBar.put(p, "§a4 ŽIVOTY");
                break;
            case 5:
                livesActionBar.put(p, "§25 ŽIVOTŮ");
                break;
            case 6:
                livesActionBar.put(p, "§26 ŽIVOTŮ");
                break;
            default:
                livesActionBar.put(p, "§cNULL");
        }
    }
}

