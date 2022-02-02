package cz.minelife.boss;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static cz.minelife.Main.main;
import static cz.minelife.items.Items.TWOLIVES_STICK;

public class OnEntityDeath implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent entityDeathEvent) {
        LivingEntity livingEntity = entityDeathEvent.getEntity();
        List<MetadataValue> metadataValueList = livingEntity.getMetadata("CUSTOM_ID");
        if (livingEntity.hasMetadata("CUSTOM_ID")) {
            for (MetadataValue value: metadataValueList) {
                if (value.asString().equals("BOSS_BABICE")) {
                    Witch boss = (Witch) livingEntity;

                    List<ItemStack> loot = new ArrayList<>();

                    loot.add(TWOLIVES_STICK.getItem());

                    for (ItemStack item: loot) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                boss.getWorld().strikeLightningEffect(boss.getLocation());
                            }
                        }.runTaskLater(main, 10L);
                        boss.getWorld().dropItem(boss.getLocation(), item);
                    }
                    Bukkit.broadcastMessage("§2§lPodařilo se! Mocná Babice padla. Náš mocný hrdina " + boss.getKiller().getDisplayName() + " ji zabil. Veselme se!");

                }
            }
        }
    }
}
