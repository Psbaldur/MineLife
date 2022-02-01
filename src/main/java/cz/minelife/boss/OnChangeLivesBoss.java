package cz.minelife.boss;

import cz.minelife.events.ChangeLivesEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnChangeLivesBoss implements Listener {
    private static int livesLost = 0;

    @EventHandler
    public void onChangeLives(ChangeLivesEvent event) {
        if (!event.isFirstTime() && event.getNewLives() < event.getOldLives()) {
            livesLost++;
            spawnBoss();
        }
    }

    private void spawnBoss() {
        if (livesLost == 5)
            new Boss();
    }
}
