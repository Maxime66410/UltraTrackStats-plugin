package org.FurranyStudio.UltraTrackStats;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class StatUpdateEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    public enum UpdateType { ADD, SET, RESET }

    private final UUID uuid;
    private final String statId;
    private final int newValue;
    private final UpdateType updateType;

    public StatUpdateEvent(UUID uuid, String statId, int newValue, UpdateType updateType) {
        this.uuid = uuid;
        this.statId = statId;
        this.newValue = newValue;
        this.updateType = updateType;
    }

    public UUID getPlayerUUID() { return uuid; }
    public String getStatId() { return statId; }
    public int getNewValue() { return newValue; }
    public UpdateType getUpdateType() { return updateType; }

    @Override
    public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
