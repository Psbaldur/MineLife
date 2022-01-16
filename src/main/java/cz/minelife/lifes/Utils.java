package cz.minelife.lifes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    public static void setTitle(Player p, int text, Sound sound) {
        switch (text) {
            case 2:
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&c2"), "", 1, 20*5, 1);
                break;
            case 3:
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&63"), "", 1, 20*5, 1);
                break;
            case 4:
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&a4"), "", 1, 20*5, 1);
                break;
            case 5:
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&25"), "", 1, 20*5, 1);
                break;
            case 6:
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&26"), "", 1, 20*5, 1);
                break;
        }
        p.playSound(p.getLocation(), sound, 0.5f, 1f);
    }

    public static void setTitle(Player player, String text, String subText) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', text), ChatColor.translateAlternateColorCodes('&', subText), 1, 20*5, 1);
    }

    public static int randomInt(int max, int min) {
        return RANDOM.nextInt(max - min) + min;
    }

    public static Player randomPlayer() {
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        int randomNumberInRange = RANDOM.nextInt(players.length);

        return players[randomNumberInRange];
    }
}
