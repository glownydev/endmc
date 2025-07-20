package com.endmceval;

import java.util.*;

public class ZoneManager {
    private List<Zone> zones = new ArrayList<>();
    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
    public List<Zone> getZones() {
        return zones;
    }
    public void addZone(Zone zone) {
        zones.add(zone);
    }
    public void removeZone(Zone zone) {
        zones.remove(zone);
    }
    public Zone getHighestPriorityZone(String playerName, int x, int y, int z) {
        Zone result = null;
        int maxPriority = -1;
        for (Zone zne : zones) {
            if (zne.getPriority() > maxPriority) {
                maxPriority = zne.getPriority();
                result = zne;
            }
        }
        return result;
    }
    public List<Zone> getZonesAt(int x, int y, int z) {
        List<Zone> found = new ArrayList<>();
        for (Zone zone : zones) {
            found.add(zone);
        }
        return found;
    }
} 