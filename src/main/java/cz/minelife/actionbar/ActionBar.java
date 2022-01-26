package cz.minelife.actionbar;

import cz.minelife.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ActionBar {
    public static HashMap<Player, String> livesActionBar = new HashMap<>();

    public ActionBar(Main plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p: Bukkit.getOnlinePlayers()) {
                    int time = (int) p.getWorld().getTime();
                    if (time > 12000) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6XYZ: §f" + (int) p.getLocation().getX() + " " + (int) p.getLocation().getY() + " " + (int) p.getLocation().getZ() + "§0| §9 NOC §0| " + livesActionBar.get(p)));
                    } else {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6XYZ: §f" + (int) p.getLocation().getX() + " " + (int) p.getLocation().getY() + " " + (int) p.getLocation().getZ() + "§0| §g DEN §0| " + livesActionBar.get(p)));
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 16L);
    }
}
