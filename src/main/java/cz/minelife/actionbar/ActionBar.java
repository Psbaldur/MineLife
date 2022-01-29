package cz.minelife.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static cz.minelife.Main.main;

public class ActionBar {
    public static HashMap<Player, String> livesActionBar = new HashMap<>();

    public ActionBar() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p: Bukkit.getOnlinePlayers()) {
                    int time = (int) p.getWorld().getTime();
                    if (time > 12000) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6XYZ: §f" + (int) p.getLocation().getX() + " " + (int) p.getLocation().getY() + " " + (int) p.getLocation().getZ() + " §0§l| §9NOC §0§l|§f§r " + livesActionBar.get(p)));
                    } else {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6XYZ: §f" + (int) p.getLocation().getX() + " " + (int) p.getLocation().getY() + " " + (int) p.getLocation().getZ() + " §0§l| §eDEN §0§l|§f§r " + livesActionBar.get(p)));
                    }
                }
            }
        }.runTaskTimer(main, 0L, 16);
    }
}
