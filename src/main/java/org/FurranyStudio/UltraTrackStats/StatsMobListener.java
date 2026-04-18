package org.FurranyStudio.UltraTrackStats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class StatsMobListener implements Listener {

    private final UltraTrackStats main;

    public StatsMobListener(UltraTrackStats main) {
        this.main = main;
    }

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        if (killer == null) return;

        main.getStatsAPI().addStat(killer, e.getEntityType().name(), 1);
    }
}
