package org.FurranyStudio.UltraTrackStats;

import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class StatsBreakListener implements Listener {

    private final UltraTrackStats main;

    public StatsBreakListener(UltraTrackStats main) {
        this.main = main;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().hasMetadata("placed_by_player")) return;

        if (e.getBlock().getBlockData() instanceof Ageable ageable) {
            if (ageable.getAge() < ageable.getMaximumAge()) return;
        }

        main.getStatsAPI().addStat(e.getPlayer(), e.getBlock().getType().name(), 1);
    }
}
