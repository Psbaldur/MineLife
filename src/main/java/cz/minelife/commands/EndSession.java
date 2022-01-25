package cz.minelife.commands;

import cz.minelife.Main;
import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static cz.minelife.teams.ETeams.ONELIVE;

public class EndSession implements CommandExecutor {
    private Main plugin;
    private Yaml yaml;

    public EndSession(Main plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (yaml.getBoogeyman() != null && !yaml.getIfIsBoogeyCured()) {
                String boogeyman = yaml.getBoogeyman();
                Player boogey = Bukkit.getPlayer(boogeyman);

                plugin.getServer().broadcastMessage("§c§lBohužel " + boogeyman + " nepřekonal své Prokletí Babicí a tak mu byly strženy životy na 1. \n\nServer se uzavře za 5 minut");

                yaml.setPlayerLives(boogey, 1);
                ONELIVE.setTeam(boogey);

                boogey.kickPlayer("§c§lVe tvém vlastním zájmu se již nenapojuj jinak budeš muset čelit všemu s jedním životem :)");
            } else {
                plugin.getServer().broadcastMessage("§c§lServer se automaticky vypne za 5 minut!");
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getServer().savePlayers();
                    Bukkit.getServer().shutdown();
                }
            }.runTaskLater(plugin, 20 * 300);
        } else {
            sender.sendMessage(ChatColor.RED + "Err.");
        }
        return true;
    }
}
