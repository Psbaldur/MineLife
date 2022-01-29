package cz.minelife.commands;

import cz.minelife.players.Boogeyman;
import cz.minelife.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static cz.minelife.Main.main;
import static cz.minelife.teams.TeamUtil.setPlayerLives;
import static cz.minelife.teams.TeamUtil.setupTeams;
import static cz.minelife.utils.Utils.randomInt;
import static cz.minelife.utils.Utils.setTitle;

public class SetupCmd implements CommandExecutor {
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
                                setupTeams();
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    int random = Utils.randomInt(6, 2);

                                    setPlayerLives(p, random);
                                    setTitle(p, random, Sound.ENTITY_PLAYER_LEVELUP);
                                }
                                this.cancel();
                                new Boogeyman().choose();
                            }
                            i++;
                        }
                    }.runTaskTimer(main, 2L, 8L);
                    this.cancel();
                }
                i++;
            }
        }.runTaskTimer(main, 0L, 2L);
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
