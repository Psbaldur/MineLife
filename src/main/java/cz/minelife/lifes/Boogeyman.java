package cz.minelife.lifes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Boogeyman implements CommandExecutor {
    private Plugin plugin;

    public Boogeyman(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp()) {

        } else {
            sender.sendMessage(ChatColor.RED + "Err");
        }
        return false;
    }
}
