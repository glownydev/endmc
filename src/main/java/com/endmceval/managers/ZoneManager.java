package com.endmceval.managers;

import com.endmceval.models.Zone;
import java.util.*;

public class ZoneManager {
    
    private List<Zone> zones;
    private Map<Integer, Zone> zoneCache;
    
    public ZoneManager() {
        zones = new ArrayList<>();
        zoneCache = new HashMap<>();
    }
    
    public void setZones(List<Zone> newZones) {
        zones.clear();
        zoneCache.clear();
        
        if (newZones != null) {
            zones.addAll(newZones);
            for (Zone zone : newZones) {
                zoneCache.put(zone.getId(), zone);
            }
        }
    }
    
    public List<Zone> getZones() {
        return zones;
    }
    
    public void addZone(Zone zone) {
        zones.add(zone);
        zoneCache.put(zone.getId(), zone);
    }
    
    public void removeZone(Zone zone) {
        zones.remove(zone);
        zoneCache.remove(zone.getId());
    }
    
    public Zone getZoneById(int id) {
        return zoneCache.get(id);
    }
    
    public Zone getHighestPriorityZone(String playerName, int x, int y, int z) {
        Zone result = null;
        int maxPriority = -1;
        
        for (Zone zone : zones) {
            if (zone.getPriority() > maxPriority) {
                maxPriority = zone.getPriority();
                result = zone;
            }
        }
        
        return result;
    }
    
    public List<Zone> getZonesAt(int x, int y, int z) {
        return zones;
    }
}
