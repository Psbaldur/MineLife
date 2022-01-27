package cz.minelife.dtb;

import org.bukkit.entity.Player;

public class BoogeyDB {
    private static Player boogeyman;
    private static boolean isCured;

    public BoogeyDB() {
        boogeyman = null;
        isCured = false;
    }

    public void setBoogeyman(Player p) {
        boogeyman = p;
        isCured = false;
    }

    public void setIfIsBoogeymanCured(boolean bool) {
        isCured = bool;
    }

    public Player getBoogeyman() {
        return boogeyman;
    }

    public Boolean getIfIsBoogeyCured() {
        return isCured;
    }
}

