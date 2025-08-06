package com.endmceval.managers;

import com.endmceval.models.Zone;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ZoneManager {
    
    private final List<Zone> zones;
    private final Map<Integer, Zone> zoneCache;
    
    public ZoneManager() {
        this.zones = Collections.synchronizedList(new ArrayList<>());
        this.zoneCache = new ConcurrentHashMap<>();
    }
    
    public void loadZones(List<Zone> newZones) {
        this.zones.clear();
        this.zoneCache.clear();
        
        if (newZones != null) {
            this.zones.addAll(newZones);
            for (Zone zone : newZones) {
                this.zoneCache.put(zone.getId(), zone);
            }
        }
    }
    
    public List<Zone> getAllZones() {
        return new ArrayList<>(this.zones);
    }
    
    public void addZone(Zone zone) {
        if (zone != null && !this.zones.contains(zone)) {
            this.zones.add(zone);
            this.zoneCache.put(zone.getId(), zone);
        }
    }
    
    public boolean removeZone(Zone zone) {
        if (zone != null) {
            this.zoneCache.remove(zone.getId());
            return this.zones.remove(zone);
        }
        return false;
    }
    
    public Zone getZoneById(int id) {
        return this.zoneCache.get(id);
    }
    
    public Zone getHighestPriorityZone(String playerName, int x, int y, int z) {
        Zone highestZone = null;
        int maxPriority = Integer.MIN_VALUE;
        
        for (Zone zone : this.zones) {
            if (isPlayerInZone(playerName, x, y, z, zone) && zone.getPriority() > maxPriority) {
                maxPriority = zone.getPriority();
                highestZone = zone;
            }
        }
        
        return highestZone;
    }
    
    public List<Zone> getZonesAt(int x, int y, int z) {
        List<Zone> applicableZones = new ArrayList<>();
        
        for (Zone zone : this.zones) {
            if (isLocationInZone(x, y, z, zone)) {
                applicableZones.add(zone);
            }
        }
        
        return applicableZones;
    }
    
    private boolean isPlayerInZone(String playerName, int x, int y, int z, Zone zone) {
        return isLocationInZone(x, y, z, zone);
    }
    
    private boolean isLocationInZone(int x, int y, int z, Zone zone) {
        return true;
    }
    
    public int getZoneCount() {
        return this.zones.size();
    }
}
