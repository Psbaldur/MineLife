package cz.minelife.commands;

import cz.minelife.Main;
import cz.minelife.players.Boogeyman;
import cz.minelife.players.Players;
import cz.minelife.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static cz.minelife.utils.Utils.randomInt;
import static cz.minelife.utils.Utils.setTitle;

public class SetupCmd implements CommandExecutor {
    private int counter = 0;
    private Main plugin;
    private HashMap<Player, Integer> lives = new HashMap<>();

    public SetupCmd(Main plugin) {
        this.plugin = plugin;
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
                                    lives.put(p, random);

                                    setTitle(p, random, Sound.ENTITY_PLAYER_LEVELUP);
                                }
                                this.cancel();
                                new Players(plugin, lives).setup();
                                new Boogeyman(plugin).choose();
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
