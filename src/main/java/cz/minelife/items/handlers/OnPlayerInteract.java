package cz.minelife.items.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static cz.minelife.items.Items.ONELIVE_STICK;
import static cz.minelife.items.Items.TWOLIVES_STICK;
import static cz.minelife.teams.TeamUtil.getPlayerLives;
import static cz.minelife.teams.TeamUtil.setPlayerLives;

public class OnPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        Player p = e.getPlayer();

        if (item == ONELIVE_STICK.getItem()) {
            int lives = getPlayerLives(p) + 1;
            if (lives > 6) {
                p.sendMessage("§7Použil jsi " + ONELIVE_STICK.getName() + "§7 a pocítil jsi jak tě nabíjí dosud nevídaná energie!");
                p.getWorld().strikeLightningEffect(p.getLocation());

                p.getInventory().remove(ONELIVE_STICK.getItem());

                setPlayerLives(p, lives);
            } else {
                p.sendMessage("§cTY NEHODNÁ OSOBO! Myslíš si, že obejdeš pravidla jo?");
            }
        } else if (item == TWOLIVES_STICK.getItem()) {
            int lives = getPlayerLives(p) + 2;
            if (lives > 6) {
                p.sendMessage("§7Použil jsi " + TWOLIVES_STICK.getName() + "§7 a pocítil jsi jak tě nabíjí dosud nevídaná energie!");
                p.getWorld().strikeLightningEffect(p.getLocation());
                p.getWorld().strikeLightningEffect(p.getLocation());

                p.getInventory().remove(TWOLIVES_STICK.getItem());

                setPlayerLives(p, lives);
            } else {
                p.sendMessage("§cTY NEHODNÁ OSOBO! Myslíš si, že obejdeš pravidla jo?");
            }
        }
    }
}
