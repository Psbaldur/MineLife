package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static cz.minelife.lifes.Utils.*;

public class SetupCmd implements CommandExecutor {
    private Yaml yaml;
    private int counter = 0;
    private JavaPlugin plugin;
    public SetupCmd(JavaPlugin plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(plugin);
    }
    private void startDrawingLives() {
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    setTitle(player, randomInt(6 , 2), Sound.BLOCK_COMPARATOR_CLICK);
                }

                if (i >= 100) {
                    new BukkitRunnable() {
                        int i = 0;

                        @Override
                        public void run() {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                setTitle(player, randomInt(6, 2), Sound.BLOCK_COMPARATOR_CLICK);
                            }

                            if (i >= 25) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    int random = Utils.randomInt(6, 2);

                                    setTitle(p, random, Sound.ENTITY_PLAYER_LEVELUP);
                                    yaml.setPlayerLives(p, random);
                                }
                                this.cancel();
                                new Players(plugin).setup();
                            }
                            i++;
                        }
                    }.runTaskTimer(plugin, 2L, 8L);
                    this.cancel();
                }
                i++;
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            this.startDrawingLives();
        } else {
            sender.sendMessage("Err");
        }
        return true;
    }
}
