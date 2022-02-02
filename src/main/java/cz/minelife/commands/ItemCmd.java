package cz.minelife.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cz.minelife.items.Items.*;

public class ItemCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (sender.isOp() && args.length >= 1) {
            if (args.length >= 2 && args[1] != null)
                p = Bukkit.getPlayer(args[1]);

            if (args[0].equalsIgnoreCase("onelive_stick")) {
                p.getInventory().addItem(ONELIVE_STICK.getItem());
                p.sendMessage("§7PŘEDMĚT BYL VYDÁN!");
                return true;
            } if (args[0].equalsIgnoreCase("twolives_stick")) {
                p.getInventory().addItem(TWOLIVES_STICK.getItem());
                p.sendMessage("§7PŘEDMĚT BYL VYDÁN!");
                return true;
            }

            sender.sendMessage("§cINVALID ITEM!");
        }
        return false;
    }
}

