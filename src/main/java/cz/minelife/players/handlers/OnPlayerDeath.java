package cz.minelife.players.handlers;

import cz.minelife.dtb.BoogeyDB;
import cz.minelife.teams.ETeams;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static cz.minelife.teams.TeamUtil.getPlayerTeam;
import static cz.minelife.teams.TeamUtil.setPlayerLives;

public class OnPlayerDeath implements Listener {
    private BoogeyDB boogeyDB;

    public OnPlayerDeath() {
        boogeyDB = new BoogeyDB();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        ETeams playerTeam = getPlayerTeam(p);
        int playerLives = playerTeam.getIntLives() - 1;

        if (p.getKiller() != null && p.getKiller() == boogeyDB.getBoogeyman() && !boogeyDB.getIfIsBoogeyCured()) {
            p.getKiller().sendMessage("§aProlomil jsi prokletí! Babice bude trochu naštvaná...");
            p.playSound(p.getLocation() , Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
            p.getWorld().strikeLightningEffect(p.getLocation());
            boogeyDB.setIfIsBoogeymanCured(true);
        }

        switch (playerLives) {
            case 0:
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel. Doufám, že už tě nikdo neoživí...");
                setPlayerLives(p, 0, false);
                break;
            case 1:
                p.sendMessage(ChatColor.RED + "Ou, škoda myslel jsem že máš na víc. No což, tvým novým úkolem je povraždit všechny.");
                setPlayerLives(p, 1, false);
                break;
            case 2:
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 2 životy. Buď opatrný!");
                setPlayerLives(p, 2, false);
                break;
            case 3:
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 3 životy. Buď opatrný!");
                setPlayerLives(p, 3, false);
                break;
            case 4:
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 4 životy. Buď opatrný!");
                setPlayerLives(p, 4, false);
                break;
            case 5:
                p.sendMessage(ChatColor.RED + "Právě jsi zemřel a ztratil život. Už ti zbývají pouze 5 životů. Buď opatrný!");
                setPlayerLives(p, 5, false);
                break;
        }
    }
}
