package cz.minelife.players.handlers;

import cz.minelife.dtb.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import static cz.minelife.teams.ETeams.*;

public class OnPlayerDeath implements Listener {
    private JavaPlugin plugin;
    private Yaml yaml;

    public OnPlayerDeath(JavaPlugin plugin) {
        this.plugin = plugin;
        yaml = new Yaml(plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Team playerTeam = p.getScoreboard().getEntryTeam(p.getDisplayName());
        int playerLives = yaml.getPlayerLives(p) - 1;
        yaml.setPlayerLives(p, playerLives);

        if (p.getKiller() != null && p.getKiller().getDisplayName().equals(yaml.getBoogeyman()) && !yaml.getIfIsBoogeyCured()) {
            Bukkit.getPlayer(yaml.getBoogeyman()).sendMessage("§aProlomil jsi prokletí! Babice bude trochu naštvaná...");
            p.playSound(p.getLocation() , Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
            yaml.setIfIsBoogeymanCured(true);
        }

        playerTeam.removeEntry(p.getDisplayName());

        switch (playerLives) {
            case 0:
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel. Doufám, že už tě nikdo neoživí...");
                break;
            case 1:
                ONELIVE.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Ou, škoda myslel jsem že máš na víc. No což, tvým novým úkolem je povraždit všechny.");
                break;
            case 2:
                TWOLIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 2 životy. Buď opatrný!");
                break;
            case 3:
                THREELIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 3 životy. Buď opatrný!");
                break;
            case 4:
                FOURLIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 4 životy. Buď opatrný!");
                break;
            case 5:
                FIVELIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 5 životů. Buď opatrný!");
                break;
        }
    }
}
