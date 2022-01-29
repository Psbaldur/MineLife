package cz.minelife.commands;

import cz.minelife.Main;
import cz.minelife.boss.Boss;
import cz.minelife.players.Boogeyman;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartSession implements CommandExecutor {
    private Main plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            new Boss();
            new Boogeyman().choose();
        } else {
            sender.sendMessage(ChatColor.RED + "Err");
        }
        return true;
    }
}
