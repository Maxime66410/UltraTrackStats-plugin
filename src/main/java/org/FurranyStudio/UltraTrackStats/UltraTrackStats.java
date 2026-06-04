package org.FurranyStudio.UltraTrackStats;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UltraTrackStats extends JavaPlugin {

    private static UltraTrackStats instance;
    private StatsDatabase database;
    private StatsAPI api;
    private final List<Listener> registeredListeners = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        try {
            if (!getDataFolder().exists()) getDataFolder().mkdir();

            saveDefaultConfig();

            this.database = new StatsDatabase(this);
            this.api = new StatsAPI(this);

            Objects.requireNonNull(getCommand("ultratrackstats")).setExecutor(new StatsCommand(this));

            registerListeners();

            getLogger().info("UltraTrackStats has been enabled!");
        } catch (Exception e) {
            getLogger().severe("Failed to initialize UltraTrackStats: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("UltraTrackStats has been disabled!");
    }

    public void reload() {
        reloadConfig();
        unregisterListeners();
        registerListeners();
        getLogger().info("UltraTrackStats reloaded.");
    }

    private void registerListeners() {
        if (!getConfig().getBoolean("listeners.enabled", false)) return;

        if (getConfig().getBoolean("listeners.track-block-break", true))
            registerListener(new StatsBreakListener(this));

        if (getConfig().getBoolean("listeners.track-block-place", true))
            registerListener(new StatsPlaceListener(this));

        if (getConfig().getBoolean("listeners.track-mob-kill", true))
            registerListener(new StatsMobListener(this));

        getLogger().info("Listeners enabled.");
    }

    private void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
        registeredListeners.add(listener);
    }

    private void unregisterListeners() {
        registeredListeners.forEach(HandlerList::unregisterAll);
        registeredListeners.clear();
    }

    public static UltraTrackStats getInstance() { return instance; }
    public StatsDatabase getStatsDatabase() { return database; }
    public StatsAPI getStatsAPI() { return api; }
}
