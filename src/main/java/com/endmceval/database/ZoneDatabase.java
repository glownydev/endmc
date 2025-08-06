package com.endmceval.database;

import com.endmceval.models.Zone;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.concurrent.CompletableFuture;
import java.util.*;

public class ZoneDatabase {
    private HikariDataSource ds;
    
    public ZoneDatabase(String file) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + file);
        config.setMaximumPoolSize(5);
        ds = new HikariDataSource(config);
        try (Connection c = ds.getConnection(); Statement s = c.createStatement()) {
            s.executeUpdate("CREATE TABLE IF NOT EXISTS zones (id INTEGER PRIMARY KEY, name TEXT, flags TEXT, priority INTEGER)");
        } catch (SQLException e) {
        }
    }
    
    public CompletableFuture<List<Zone>> loadZonesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Zone> zones = new ArrayList<>();
            try (Connection c = ds.getConnection(); Statement s = c.createStatement()) {
                ResultSet rs = s.executeQuery("SELECT * FROM zones");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String flagsStr = rs.getString("flags");
                    int priority = rs.getInt("priority");
                    Map<String, Object> flags = new HashMap<>();
                    for (String f : flagsStr.split(",")) {
                        String[] kv = f.split(":");
                        if (kv.length == 2) flags.put(kv[0], kv[1]);
                    }
                    zones.add(new Zone(id, name, flags, priority));
                }
            } catch (SQLException e) {
            }
            return zones;
        });
    }
    
    public CompletableFuture<Void> saveZoneAsync(Zone zone) {
        return CompletableFuture.runAsync(() -> {
            try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement("REPLACE INTO zones (id, name, flags, priority) VALUES (?, ?, ?, ?);")) {
                ps.setInt(1, zone.getId());
                ps.setString(2, zone.getName());
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Object> e : zone.getFlags().entrySet()) {
                    sb.append(e.getKey()).append(":").append(e.getValue()).append(",");
                }
                ps.setString(3, sb.toString());
                ps.setInt(4, zone.getPriority());
                ps.executeUpdate();
            } catch (SQLException e) {
            }
        });
    }
    
    public void close() {
        ds.close();
    }
}
