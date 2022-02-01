package cz.minelife.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChangeLivesEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Integer newLives;
    private final Integer oldLives;
    private boolean isCancelled;
    private final boolean isFirstTime;

    public ChangeLivesEvent(Player player, Integer newLives, Integer oldLives, boolean isFirstTime) {
        this.player = player;
        this.newLives = newLives;
        this.oldLives = oldLives;
        this.isFirstTime = isFirstTime;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getNewLives() {
        return newLives;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    public int getOldLives() {
        return oldLives;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }
}
