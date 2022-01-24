package cz.minelife.players;

import cz.minelife.dtb.Yaml;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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


public class Players implements Listener, CommandExecutor {
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
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        int playerLives = yaml.getPlayerLives(p);

        p.setScoreboard(scoreboard);

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args[0] != null) {
            Player receiver = Bukkit.getPlayer(args[0]);
            Player santa = (Player) sender;

            int receiverLives = yaml.getPlayerLives(receiver);
            int santaLives = yaml.getPlayerLives(santa);

            if ((receiverLives + 1) >= 7) {
                santa.sendMessage("§cHráč, který má obdržet život nemůže mít 7 životů!");
                return false;
            }

            yaml.setPlayerLives(receiver, receiverLives + 1);
            yaml.setPlayerLives(santa, santaLives - 1);

            receiver.playEffect(EntityEffect.TOTEM_RESURRECT);
            santa.playSound(receiver.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);

            this.addPlayerToTeam(santa);
            this.addPlayerToTeam(receiver);
        } else {

        }
        return true;
    }
}