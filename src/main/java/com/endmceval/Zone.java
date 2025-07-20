package com.endmceval;

import java.util.Map;

public class Zone {
    private int id;
    private String name;
    private Map<String, Object> flags;
    private int priority;
    public Zone(int id, String name, Map<String, Object> flags, int priority) {
        this.id = id;
        this.name = name;
        this.flags = flags;
        this.priority = priority;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public Map<String, Object> getFlags() { return flags; }
    public int getPriority() { return priority; }
    public Object getFlag(String key) { return flags.get(key); }
    public void setFlag(String key, Object value) { flags.put(key, value); }
} 