package cz.minelife.commands;

import cz.minelife.dtb.Yaml;
import cz.minelife.teams.ETeams;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static cz.minelife.teams.TeamUtil.*;

public class GiveLive implements CommandExecutor {
    private JavaPlugin plugin;
    private Yaml yaml;

    public GiveLive(JavaPlugin plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args[0] != null) {
            Player receiver = Bukkit.getPlayer(args[0]);
            Player santa = (Player) sender;

            ETeams receiverTeam = getPlayerTeam(receiver);
            ETeams santaTeam = getPlayerTeam(santa);

            int receiverLives = receiverTeam.getIntLives() + 1;
            int santaLives = santaTeam.getIntLives() - 1;

            if (receiverLives >= 7) {
                santa.sendMessage("§cHráč, který má obdržet život nemůže mít 7 životů!");
                return false;
            }

            yaml.setPlayerLives(receiver, receiverLives);
            yaml.setPlayerLives(santa, santaLives);

            receiver.playEffect(EntityEffect.TOTEM_RESURRECT);
            santa.playSound(receiver.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);

            getTeamByLiveCount(santaLives).setTeam(santa);
            getTeamByLiveCount(receiverLives).setTeam(receiver);
        } else {
            return false;
        }
        return true;
    }
}
