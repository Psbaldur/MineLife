package cz.minelife.boss;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Witch;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Boss {
    private final Location spawnPoint = Bukkit.getWorld("world").getSpawnLocation();
    private Witch witch = (Witch) Bukkit.getWorld("world").spawnEntity(spawnPoint, EntityType.WITCH);

    public Boss() {
        witch.setCustomName("§c§lMOCNÁ BABICE");
        witch.setCustomNameVisible(true);
        witch.setGlowing(true);
        witch.setHealth(26);
        witch.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 2));
        witch.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2));
        witch.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1000000, 25));

        Bukkit.broadcastMessage("§cBuďte na pozoru! Objevila se tu Babice");
    }
}
