package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
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
                    setTitle(player, randomInt());
                }

                if (i >= 100) {
                    new BukkitRunnable() {
                        int i = 0;

                        @Override
                        public void run() {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                setTitle(player, randomInt());
                            }

                            if (i >= 25) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    int random = Utils.randomInt();

                                    setTitle(p, random);
                                    yaml.setPlayerLives(p, random);
                                }
                                this.cancel();
                                new Players(plugin).setup().addPlayersToTeams();
                            }
                            i++;
                        }
                    }.runTaskTimer(plugin, 2L, 5L);
                    this.cancel();
                }
                i++;
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.isOp()) {
            this.startDrawingLives();
        } else {
            p.sendMessage("Nemáš oprávnění!!!");
        }
        return true;
    }
}
