package com.endmceval;

public class ZoneFlag {
    private String name;
    private Object value;
    public ZoneFlag(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    public String getName() { return name; }
    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }
} 