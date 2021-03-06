package cz.minelife.players;

import cz.minelife.dtb.BoogeyDB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static cz.minelife.Main.main;
import static cz.minelife.teams.ETeams.ONELIVE;
import static cz.minelife.teams.TeamUtil.getPlayerTeam;
import static cz.minelife.utils.Utils.randomPlayer;
import static cz.minelife.utils.Utils.setTitle;

public class Boogeyman {
    private BoogeyDB boogeyDB;

    public Boogeyman() {
        this.boogeyDB = new BoogeyDB();
    }

    public void choose() {
        new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                for (Player p: Bukkit.getOnlinePlayers()) {
                    if (getPlayerTeam(p) == ONELIVE)
                        players.remove(p);
                }
                Player p = randomPlayer(players);

                setTitle(p, "&cBYL JSI PROKLET BABICÍ", "");
                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1f, 1f);
                p.sendMessage(ChatColor.RED + "Byl jsi proklet Babicí. Odteď do konce dnešního sezení musíš zabít jednoho hráče, aby jsi se vyléčil. V případě, že hráče nezabiješ stihne tě trest a to taký, že příště budeš mít pouze 1 život.");
                boogeyDB.setBoogeyman(p);

                Bukkit.broadcastMessage("§cVšemocná čarodějnice Babice se rozmáchla a jednoho z nás proklela. Dávejte si na něj pozor!");
            }
        }.runTaskLater(main, 20*15);
    }
}
