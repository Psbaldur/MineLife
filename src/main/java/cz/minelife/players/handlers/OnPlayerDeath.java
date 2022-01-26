package cz.minelife.players.handlers;

import cz.minelife.Main;
import cz.minelife.dtb.Yaml;
import cz.minelife.teams.ETeams;
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

import static cz.minelife.actionbar.ActionBar.livesActionBar;
import static cz.minelife.teams.ETeams.*;
import static cz.minelife.teams.TeamUtil.getPlayerTeam;

public class OnPlayerDeath implements Listener {
    private Main plugin;
    private Yaml yaml;

    public OnPlayerDeath(Main plugin) {
        this.plugin = plugin;
        yaml = new Yaml(plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        ETeams playerTeam = getPlayerTeam(p);
        int playerLives = playerTeam.getIntLives() - 1;

        if (p.getKiller() != null && p.getKiller().getDisplayName().equals(yaml.getBoogeyman()) && !yaml.getIfIsBoogeyCured()) {
            Bukkit.getPlayer(yaml.getBoogeyman()).sendMessage("§aProlomil jsi prokletí! Babice bude trochu naštvaná...");
            p.playSound(p.getLocation() , Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
            yaml.setIfIsBoogeymanCured(true);
        }

        switch (playerLives) {
            case 0:
                p.setGameMode(GameMode.SPECTATOR);
                playerTeam.removePlayer(p);
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel. Doufám, že už tě nikdo neoživí...");
                break;
            case 1:
                ONELIVE.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Ou, škoda myslel jsem že máš na víc. No což, tvým novým úkolem je povraždit všechny.");
                livesActionBar.replace(p, "§41 ŽIVOT");
                break;
            case 2:
                TWOLIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 2 životy. Buď opatrný!");
                livesActionBar.replace(p, "§c2 ŽIVOTY");
                break;
            case 3:
                THREELIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 3 životy. Buď opatrný!");
                livesActionBar.replace(p, "§63 ŽIVOTY");
                break;
            case 4:
                FOURLIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 4 životy. Buď opatrný!");
                livesActionBar.replace(p, "§a4 ŽIVOTY");
                break;
            case 5:
                FIVELIVES.getTeam().addEntry(p.getDisplayName());
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 5 životů. Buď opatrný!");
                livesActionBar.replace(p, "§25 ŽIVOTŮ");
                break;
        }
    }
}
