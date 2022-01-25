package cz.minelife.commands.givelive;

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
        if (sender instanceof Player && args[0] != null && !((Player) sender).getDisplayName().equals(args[0])) {

            Player santa = (Player) sender;
            Player receiver = Bukkit.getPlayer(args[0]);

            GiveLiveGUI gui = new GiveLiveGUI(santa, receiver, yaml);

        } else {
            sender.sendMessage("§cSomething went wrong :(");
            return false;
        }
        return true;
    }
}
