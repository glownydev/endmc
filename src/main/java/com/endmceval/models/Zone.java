package com.endmceval.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Zone {
    
    private final int id;
    private final String name;
    private final Map<String, Object> flags;
    private final int priority;
    
    public Zone(int id, String name, Map<String, Object> flags, int priority) {
        this.id = id;
        this.name = name != null ? name : "Unknown";
        this.flags = flags != null ? new HashMap<>(flags) : new HashMap<>();
        this.priority = priority;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Map<String, Object> getFlags() {
        return new HashMap<>(this.flags);
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public Object getFlag(String key) {
        return this.flags.get(key);
    }
    
    public boolean hasFlag(String key) {
        return this.flags.containsKey(key);
    }
    
    public void setFlag(String key, Object value) {
        if (key != null) {
            this.flags.put(key, value);
        }
    }
    
    public Object removeFlag(String key) {
        return this.flags.remove(key);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Zone zone = (Zone) obj;
        return id == zone.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", flagCount=" + flags.size() +
                '}';
    }
}
