package cz.minelife.items.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static cz.minelife.items.Items.ONELIVE_STICK;
import static cz.minelife.items.Items.TWOLIVES_STICK;
import static cz.minelife.teams.TeamUtil.getPlayerLives;
import static cz.minelife.teams.TeamUtil.setPlayerLives;

public class OnPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getItem() == null || !e.getItem().getItemMeta().hasCustomModelData() || e.getAction() == Action.PHYSICAL || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            return;
        }

        if (e.getItem().getItemMeta().getCustomModelData() == ONELIVE_STICK.getItem().getItemMeta().getCustomModelData()) {
            int lives = getPlayerLives(p) + 1;
            Bukkit.getLogger().info(String.valueOf(lives));
            if (lives < 7) {
                p.sendMessage("§7Použil jsi " + ONELIVE_STICK.getName() + "§7 a pocítil jsi jak tě nabíjí dosud nevídaná energie!");
                p.getWorld().strikeLightningEffect(p.getLocation());

                p.getInventory().remove(ONELIVE_STICK.getItem());

                setPlayerLives(p, lives);
            } else {
                p.sendMessage("§cTY NEHODNÁ OSOBO! Myslíš si, že obejdeš pravidla jo?");
            }
            return;
        } else if (e.getItem().getItemMeta().getCustomModelData() == TWOLIVES_STICK.getItem().getItemMeta().getCustomModelData()) {
            int lives = getPlayerLives(p) + 2;
            if (lives < 7) {
                p.sendMessage("§7Použil jsi " + TWOLIVES_STICK.getName() + "§7 a pocítil jsi jak tě nabíjí dosud nevídaná energie!");
                p.getWorld().strikeLightningEffect(p.getLocation());
                p.getWorld().strikeLightningEffect(p.getLocation());

                p.getInventory().remove(TWOLIVES_STICK.getItem());

                setPlayerLives(p, lives);
            } else {
                p.sendMessage("§cTY NEHODNÁ OSOBO! Myslíš si, že obejdeš pravidla jo?");
            }
            return;
        }
    }
}
