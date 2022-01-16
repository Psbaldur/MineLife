package cz.minelife.lifes;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


public class Players implements Listener {
    private JavaPlugin plugin;
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard scoreboard = manager.getMainScoreboard();
    private Yaml yaml;
    private Team oneLive;
    private Team twoLives;
    private Team threeLives;
    private Team fourLives;
    private Team fiveLives;
    private Team sixLives;

    public Players(JavaPlugin plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml(this.plugin);
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
                    break;
                case 2:
                    twoLives.addEntry(p.getDisplayName());
                    break;
                case 3:
                    threeLives.addEntry(p.getDisplayName());
                    break;
                case 4:
                    fourLives.addEntry(p.getDisplayName());
                    break;
                case 5:
                    fiveLives.addEntry(p.getDisplayName());
                    break;
                case 6:
                    sixLives.addEntry(p.getDisplayName());
                    break;
            }
        }
    }

    public void addPlayerToTeam(Player p) {
        int playerLives = yaml.getPlayerLives(p);
        Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());

        if (playerTeam != null)
            playerTeam.removeEntry(p.getDisplayName());

        switch (playerLives) {
            case 1:
                oneLive.addEntry(p.getDisplayName());
                break;
            case 2:
                twoLives.addEntry(p.getDisplayName());
                break;
            case 3:
                threeLives.addEntry(p.getDisplayName());
                break;
            case 4:
                fourLives.addEntry(p.getDisplayName());
                break;
            case 5:
                fiveLives.addEntry(p.getDisplayName());
                break;
            case 6:
                sixLives.addEntry(p.getDisplayName());
                break;
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());
        int playerLives = yaml.getPlayerLives(p) - 1;
        yaml.setPlayerLives(p, playerLives);

        if (p.getKiller() != null && p.getKiller().getDisplayName().equals(yaml.getBoogeyman())) {
            Bukkit.getPlayer(yaml.getBoogeyman()).sendMessage("§aProlomil jsi prokletí! Babice bude trochu naštvaná...");
            p.playSound(p.getLocation() , Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
            yaml.setIfIsBoogeyCured(true);
        }

        assert playerTeam != null;
        playerTeam.removeEntry(p.getDisplayName());

        switch (playerLives) {
            case 0:
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel. Doufám, že už tě nikdo neoživí...");
                break;
            case 1:
                oneLive.addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Ou, škoda myslel jsem že máš na víc. No což, tvým novým úkolem je povraždit všechny.");
                break;
            case 2:
                twoLives.addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 2 životy. Buď opatrný!");
                break;
            case 3:
                threeLives.addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 3 životy. Buď opatrný!");
                break;
            case 4:
                fourLives.addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 4 životy. Buď opatrný!");
                break;
            case 5:
                fiveLives.addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 5 životů. Buď opatrný!");
                break;
        }
    }

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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        int playerLives = yaml.getPlayerLives(p);

        switch (playerLives) {
            case 1:
                oneLive.addEntry(p.getDisplayName());
                break;
            case 2:
                twoLives.addEntry(p.getDisplayName());
                break;
            case 3:
                threeLives.addEntry(p.getDisplayName());
                break;
            case 4:
                fourLives.addEntry(p.getDisplayName());
                break;
            case 5:
                fiveLives.addEntry(p.getDisplayName());
                break;
            case 6:
                sixLives.addEntry(p.getDisplayName());
                break;
        }
    }
}