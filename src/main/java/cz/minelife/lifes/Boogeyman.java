package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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
        return true;
    }

    public void choose() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player randomPlayer = randomPlayer();

                for (Player p: Bukkit.getOnlinePlayers()) {
                    if (p.getDisplayName().equals(randomPlayer.getDisplayName())) {
                        setTitle(p, "&cBYL JSI PROKLET BABICÍ", "");
                        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "Byl jsi proklet Babicí. Odteď do konce dnešního sezení musíš zabít jednoho hráče, aby jsi se vyléčil. V případě, že hráče nezabiješ stihne tě trest a to taký, že příště budeš mít pouze 1 život.");
                        yaml.setBoogeyman(p);
                    } else {
                        setTitle(p, "&aJSI ZDRAVÝ A ČILÝ", "TO ZNAMENÁ, ŽE SI NEBYL PROKLET");
                        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1f, 1f);
                    }
                }
            }
        }.runTaskLater(plugin, 20*900);
    }
}
