package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Objects;
import java.util.UUID;

public class Players implements Listener {
    private JavaPlugin plugin;
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard scoreboard = manager.getMainScoreboard();
    private Yaml yaml;
    Team oneLive;
    Team twoLives;
    Team threeLives;
    Team fourLives;
    Team fiveLives;
    Team sixLives;

    public Players(JavaPlugin plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(this.plugin);
        this.setup();
    }

    public void setup() {
        for (Team t: scoreboard.getTeams()) {
            t.unregister();
        }

        oneLive = scoreboard.registerNewTeam("oneLive");
        twoLives = scoreboard.registerNewTeam("twoLives");
        threeLives = scoreboard.registerNewTeam("threeLives");
        fourLives = scoreboard.registerNewTeam("fourLives");
        fiveLives = scoreboard.registerNewTeam("fiveLives");
        sixLives = scoreboard.registerNewTeam("sixLives");

        oneLive.setColor(ChatColor.DARK_RED);
        twoLives.setColor(ChatColor.RED);
        threeLives.setColor(ChatColor.GOLD);
        fourLives.setColor(ChatColor.GREEN);
        fiveLives.setColor(ChatColor.DARK_GREEN);
        sixLives.setColor(ChatColor.DARK_GREEN);
        this.addPlayersToTeams();
    }

    public void addPlayersToTeams() {
        for (Player p: Bukkit.getOnlinePlayers()) {
            int lives = yaml.getPlayerLives(p);
            Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());

            if (playerTeam != null)
                playerTeam.removeEntry(p.getDisplayName());

            switch (lives) {
                case 1:
                    oneLive.addEntry(p.getDisplayName());
                case 2:
                    twoLives.addEntry(p.getDisplayName());
                case 3:
                    threeLives.addEntry(p.getDisplayName());
                case 4:
                    fourLives.addEntry(p.getDisplayName());
                case 5:
                    fiveLives.addEntry(p.getDisplayName());
                case 6:
                    sixLives.addEntry(p.getDisplayName());
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());

        if (playerTeam == null)
            return;

        int playerLives = yaml.getPlayerLives(p);
        playerTeam.removeEntry(p.getDisplayName());

        yaml.setPlayerLives(p, playerLives - 1);

        switch (playerLives - 1) {
            case 0:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lTy už jsi stihl vyplýtvat životy? No nic, zde máš spectatora."));
                p.setGameMode(GameMode.SPECTATOR);
            case 1:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNo a to čeho jsi se obával je zde, odteď je tvým jediným cílem zabít ostatní hráče."));
                oneLive.addEntry(p.getDisplayName());
            case 2:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lZemřel jsi a ztratil jsi život. Tvůj aktuální počet životů je dva. Měj se na pozoru!"));
                twoLives.addEntry(p.getDisplayName());
            case 3:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lZemřel jsi a ztratil jsi život. Tvůj aktuální počet životů je tři. Měj se na pozoru!"));
                threeLives.addEntry(p.getDisplayName());
            case 4:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lZemřel jsi a ztratil jsi život. Tvůj aktuální počet životů je čtyři. Měj se na pozoru!"));
                fourLives.addEntry(p.getDisplayName());
            case 5:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lZemřel jsi a ztratil jsi život. Tvůj aktuální počet životů je pět. Měj se na pozoru!"));
                fiveLives.addEntry(p.getDisplayName());
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Team playerTeam = scoreboard.getEntryTeam(e.getPlayer().getDisplayName());
        if (playerTeam != null) {
            switch(playerTeam.getName()) {
                case "oneLive":
                    e.setFormat(ChatColor.DARK_RED + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                case "twoLives":
                    e.setFormat(ChatColor.RED + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                case "threeLives":
                    e.setFormat(ChatColor.GOLD + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                case "fourLives":
                    e.setFormat(ChatColor.GREEN + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                case "fiveLives":
                    e.setFormat(ChatColor.DARK_GREEN + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                case "sixLives":
                    e.setFormat(ChatColor.DARK_GREEN + "<" + e.getPlayer().getDisplayName() + "> " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());

        if (playerTeam != null) {
            playerTeam.removeEntry(p.getDisplayName());
            int playerLives = yaml.getPlayerLives(p);

            switch (playerLives) {
                case 1:
                    oneLive.addEntry(p.getDisplayName());
                case 2:
                    twoLives.addEntry(p.getDisplayName());
                case 3:
                    threeLives.addEntry(p.getDisplayName());
                case 4:
                    fourLives.addEntry(p.getDisplayName());
                case 5:
                    fiveLives.addEntry(p.getDisplayName());
                case 6:
                    sixLives.addEntry(p.getDisplayName());
            }
        }
    }
}