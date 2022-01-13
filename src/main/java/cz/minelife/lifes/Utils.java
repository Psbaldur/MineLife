package cz.minelife.lifes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    private static final Random RANDOM = new Random();
    public static void setTitle(Player player, int text) {
        switch (text) {
            case 2:
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&c2"), "", 1, 20*5, 1);
                break;
            case 3:
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&63"), "", 1, 20*5, 1);
                break;
            case 4:
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&a4"), "", 1, 20*5, 1);
                break;
            case 5:
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&25"), "", 1, 20*5, 1);
                break;
            case 6:
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&26"), "", 1, 20*5, 1);
                break;
        }
    }
    public static int randomInt() {
        return RANDOM.nextInt(6 - 2) + 2;
    }
}
