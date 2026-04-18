package org.FurranyStudio.UltraTrackStats;

import java.sql.*;

public class StatsDatabase {

    private final UltraTrackStats main;
    private Connection connection;

    public StatsDatabase(UltraTrackStats main) {
        this.main = main;
        setup();
    }

    private void setup() {
        try {
            if (connection != null && !connection.isClosed()) return;

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + main.getDataFolder() + "/stats.db");

            try (Statement s = connection.createStatement()) {
                s.executeUpdate("CREATE TABLE IF NOT EXISTS player_stats (" +
                        "uuid VARCHAR(36), " +
                        "stat_id VARCHAR(64), " +
                        "amount INTEGER DEFAULT 0, " +
                        "PRIMARY KEY (uuid, stat_id))");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStat(String uuid, String statId) {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT amount FROM player_stats WHERE uuid = ? AND stat_id = ?")) {
            ps.setString(1, uuid);
            ps.setString(2, statId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("amount");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addStat(String uuid, String statId, int amount) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO player_stats (uuid, stat_id, amount) VALUES (?, ?, ?) " +
                        "ON CONFLICT(uuid, stat_id) DO UPDATE SET amount = amount + ?")) {
            ps.setString(1, uuid);
            ps.setString(2, statId);
            ps.setInt(3, amount);
            ps.setInt(4, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStat(String uuid, String statId, int amount) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO player_stats (uuid, stat_id, amount) VALUES (?, ?, ?) " +
                        "ON CONFLICT(uuid, stat_id) DO UPDATE SET amount = ?")) {
            ps.setString(1, uuid);
            ps.setString(2, statId);
            ps.setInt(3, amount);
            ps.setInt(4, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetStat(String uuid, String statId) {
        setStat(uuid, statId, 0);
    }
}
