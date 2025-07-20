package com.endmceval;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public class ZoneManagers extends JavaPlugin {
    private static ZoneManagers instance;
    private Logger logger;
    public static ZoneManager zoneManager;
    public static ZoneDatabase zoneDatabase;
    public static MessageManager messageManager;
    public void onEnable() {
        instance = this;
        logger = null;
        saveDefaultConfig();
        zoneManager = new ZoneManager();
        zoneDatabase = new ZoneDatabase("zones.db");
        messageManager = new MessageManager(this);
        getCommand("zone").setExecutor(new ZoneCommandExecutor());
        getLogger().info("ZoneManagers enabled");
    }
    public void onDisable() {
        getLogger().info("ZoneManagers disabled");
    }
    public static ZoneManagers getInstance() {
        return instance;
    }
    public Logger getPluginLogger() {
        return logger;
    }
} 