package cz.minelife.players.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class OnPlayerChat implements Listener {
    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Team playerTeam = scoreboard.getEntryTeam(e.getPlayer().getDisplayName());
        if (playerTeam != null) {
            switch(playerTeam.getName()) {
                case "oneLive":
                    e.setFormat(ChatColor.DARK_RED + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                    break;
                case "twoLives":
                    e.setFormat(ChatColor.RED + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                    break;
                case "threeLives":
                    e.setFormat(ChatColor.GOLD + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                    break;
                case "fourLives":
                    e.setFormat(ChatColor.GREEN + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                    break;
                case "fiveLives":
                case "sixLives":
                    e.setFormat(ChatColor.DARK_GREEN + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                    break;
            }
        }
    }
}
