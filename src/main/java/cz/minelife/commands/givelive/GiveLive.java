package cz.minelife.commands.givelive;

import cz.minelife.Main;
import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GiveLive implements CommandExecutor {
    public static HashMap<Player, Player> currentSantas = new HashMap<>();
    private Main plugin;
    private Yaml yaml;
    private Player santa;
    private Player receiver;

    public GiveLive(Main plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args[0] != null && !((Player) sender).getDisplayName().equals(args[0])) {

            santa = (Player) sender;
            receiver = Bukkit.getPlayer(args[0]);

            currentSantas.put(santa, receiver);

            GiveLiveGUI gui = new GiveLiveGUI(santa, receiver, yaml);

            return true;
        }
        sender.sendMessage("§cSomething went wrong :(");
        return false;
    }
}
