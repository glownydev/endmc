package com.endmceval;

public class ZoneRule {
    private String key;
    private Object value;
    public ZoneRule(String key, Object value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() { return key; }
    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }
} 