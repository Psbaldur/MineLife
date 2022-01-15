package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static cz.minelife.lifes.Utils.randomPlayer;
import static cz.minelife.lifes.Utils.setTitle;

public class Boogeyman implements CommandExecutor {
    private JavaPlugin plugin;
    private Yaml yaml;

    public Boogeyman(JavaPlugin plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp()) {
            this.choose();
        } else {
            sender.sendMessage(ChatColor.RED + "Err");
        }
        return false;
    }

    private void choose() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player randomPlayer = randomPlayer();

                for (Player p: Bukkit.getOnlinePlayers()) {
                    if (p.getDisplayName().equals(randomPlayer.getDisplayName())) {
                        setTitle(p, "&cYOU ARE BOOGEYMAN", "");
                        yaml.config.set("players." + p.getDisplayName() + ".isBoogey", true);
                    } else {
                        setTitle(p, "&aYOU ARE INNOCENT", "");
                        yaml.config.set("players." + p.getDisplayName() + ".isBoogey", false);
                    }
                }
            }
        }.runTaskLater(plugin, 10L);
    }
}
