package org.FurranyStudio.UltraTrackStats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StatsAPI {

    private final UltraTrackStats main;

    public StatsAPI(UltraTrackStats main) {
        this.main = main;
    }

    public int getStat(Player player, String statId) {
        return getStat(player.getUniqueId(), statId);
    }

    public int getStat(UUID uuid, String statId) {
        return main.getStatsDatabase().getStat(uuid.toString(), statId);
    }

    public void addStat(Player player, String statId, int amount) {
        addStat(player.getUniqueId(), statId, amount);
    }

    public void addStat(UUID uuid, String statId, int amount) {
        main.getStatsDatabase().addStat(uuid.toString(), statId, amount);
        int newValue = main.getStatsDatabase().getStat(uuid.toString(), statId);
        fireEvent(new StatUpdateEvent(uuid, statId, newValue, StatUpdateEvent.UpdateType.ADD));
    }

    public void setStat(Player player, String statId, int amount) {
        setStat(player.getUniqueId(), statId, amount);
    }

    public void setStat(UUID uuid, String statId, int amount) {
        main.getStatsDatabase().setStat(uuid.toString(), statId, amount);
        fireEvent(new StatUpdateEvent(uuid, statId, amount, StatUpdateEvent.UpdateType.SET));
    }

    public void resetStat(Player player, String statId) {
        resetStat(player.getUniqueId(), statId);
    }

    public void resetStat(UUID uuid, String statId) {
        main.getStatsDatabase().resetStat(uuid.toString(), statId);
        fireEvent(new StatUpdateEvent(uuid, statId, 0, StatUpdateEvent.UpdateType.RESET));
    }

    private void fireEvent(StatUpdateEvent event) {
        if (Bukkit.isPrimaryThread()) {
            Bukkit.getPluginManager().callEvent(event);
        } else {
            Bukkit.getScheduler().runTask(main, () -> Bukkit.getPluginManager().callEvent(event));
        }
    }

    public static StatsAPI get() {
        return UltraTrackStats.getInstance().getStatsAPI();
    }
}
