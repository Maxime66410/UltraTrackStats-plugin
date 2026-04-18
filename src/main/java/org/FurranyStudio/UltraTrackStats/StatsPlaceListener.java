package org.FurranyStudio.UltraTrackStats;

import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class StatsPlaceListener implements Listener {

    private final UltraTrackStats main;

    public StatsPlaceListener(UltraTrackStats main) {
        this.main = main;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!(e.getBlock().getBlockData() instanceof Ageable)) {
            e.getBlock().setMetadata("placed_by_player", new FixedMetadataValue(main, true));
        }

        main.getStatsAPI().addStat(e.getPlayer(), e.getBlock().getType().name(), 1);
    }
}
