package cz.minelife.commands;

import cz.minelife.dtb.BoogeyDB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static cz.minelife.Main.main;
import static cz.minelife.teams.TeamUtil.setPlayerLives;

public class EndSession implements CommandExecutor {
    private BoogeyDB boogeyDB;

    public EndSession() {
        this.boogeyDB = new BoogeyDB();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (boogeyDB.getBoogeyman() != null && !boogeyDB.getIfIsBoogeyCured()) {
                Player boogey = boogeyDB.getBoogeyman();

                main.getServer().broadcastMessage("§c§lBohužel " + boogey.getDisplayName() + " nepřekonal své Prokletí Babicí a tak mu byly strženy životy na 1. \n\nServer se uzavře za 5 minut");

                setPlayerLives(boogey, 1);

                boogey.kickPlayer("§c§lVe tvém vlastním zájmu se již nenapojuj jinak budeš muset čelit všemu s jedním životem :)");
            } else {
                main.getServer().broadcastMessage("§c§lServer se automaticky vypne za 5 minut!");
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getServer().savePlayers();
                    Bukkit.getServer().shutdown();
                }
            }.runTaskLater(main, 20 * 300);
        } else {
            sender.sendMessage(ChatColor.RED + "Err.");
        }
        return true;
    }
}
