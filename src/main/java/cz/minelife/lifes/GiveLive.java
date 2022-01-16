package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
            Players players = new Players(plugin);

            assert receiver != null;

            int receiverLives = yaml.getPlayerLives(receiver);
            int santaLives = yaml.getPlayerLives(santa);

            yaml.setPlayerLives(receiver, receiverLives + 1);
            yaml.setPlayerLives(santa, santaLives - 1);

            receiver.playEffect(EntityEffect.TOTEM_RESURRECT);
            santa.playSound(receiver.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);

            players.addPlayerToTeam(santa);
            players.addPlayerToTeam(receiver);
        }
        return true;
    }
}
