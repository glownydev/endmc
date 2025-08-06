package com.endmceval.models;

import java.util.Map;

public class ZonePreset {
    private String name;
    private Map<String, Object> flags;
    
    public ZonePreset(String name, Map<String, Object> flags) {
        this.name = name;
        this.flags = flags;
    }
    
    public String getName() { return name; }
    public Map<String, Object> getFlags() { return flags; }
}
