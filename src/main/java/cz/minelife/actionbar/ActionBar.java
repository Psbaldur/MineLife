package cz.minelife.actionbar;

import cz.minelife.Main;
import cz.minelife.teams.ETeams;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static cz.minelife.teams.TeamUtil.getPlayerTeam;

public class ActionBar {
    public static HashMap<Player, String> livesActionBar = new HashMap<>();

    public ActionBar(Main plugin) {
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
        }.runTaskTimer(plugin, 0L, 16L);
    }
    public static void addToHashMap(Player p) {
        ETeams team = getPlayerTeam(p);
        switch (team) {
            case ONELIVE:
                livesActionBar.put(p, "§41 ŽIVOT");
                break;
            case TWOLIVES:
                livesActionBar.put(p, "§c2 ŽIVOTY");
                break;
            case THREELIVES:
                livesActionBar.put(p, "§63 ŽIVOTY");
                break;
            case FOURLIVES:
                livesActionBar.put(p, "§a4 ŽIVOTY");
                break;
            case FIVELIVES:
                livesActionBar.put(p, "§25 ŽIVOTŮ");
                break;
            case SIXLIVES:
                livesActionBar.put(p, "§26 ŽIVOTŮ");
                break;
            default:
                livesActionBar.put(p, "§cNULL");
        }
    }
}
