package cz.minelife.items.handlers;

import cz.minelife.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import static cz.minelife.Main.main;
import static cz.minelife.items.Items.ONELIVE_STICK;
import static cz.minelife.items.Items.TWOLIVES_STICK;
import static cz.minelife.teams.TeamUtil.*;

public class OnPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();

        if (e.getItem() == null || !e.getItem().getItemMeta().hasCustomModelData() || e.getHand() != EquipmentSlot.HAND || action == Action.PHYSICAL || action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK || getPlayerTeam(p) == null) {
            return;
        }

        if (e.getItem().getItemMeta().getCustomModelData() == ONELIVE_STICK.getItem().getItemMeta().getCustomModelData()) {
            int lives = getPlayerLives(p) + 1;
            if (lives < 7) {
                p.sendMessage("§7Použil jsi " + ONELIVE_STICK.getName() + "§7 a pocítil jsi jak tě nabíjí dosud nevídaná energie!");
                p.getWorld().strikeLightningEffect(p.getLocation());

                this.removeItem(ONELIVE_STICK, p);

                setPlayerLives(p, lives, false);
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

                this.removeItem(TWOLIVES_STICK, p);

                setPlayerLives(p, lives, false);
            } else {
                p.sendMessage("§cTY NEHODNÁ OSOBO! Myslíš si, že obejdeš pravidla jo?");
            }
            return;
        }
    }

    private void removeItem(final Items item, final Player player) {
        Bukkit.getScheduler().runTaskLater(main, () -> player.getInventory().removeItem(item.getItem()), 10L);
    }
}
