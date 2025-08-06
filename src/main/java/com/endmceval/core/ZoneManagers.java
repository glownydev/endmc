package com.endmceval.core;

import com.endmceval.commands.ZoneCommandExecutor;
import com.endmceval.database.ZoneDatabase;
import com.endmceval.managers.MessageManager;
import com.endmceval.managers.ZoneManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ZoneManagers extends JavaPlugin {
    
    private static ZoneManagers instance;
    
    public static ZoneManager zoneManager;
    public static ZoneDatabase zoneDatabase;
    public static MessageManager messageManager;
    
    @Override
    public void onEnable() {
        instance = this;
        
        this.saveDefaultConfig();
        
        messageManager = new MessageManager(this);
        zoneManager = new ZoneManager();
        zoneDatabase = new ZoneDatabase(this.getDataFolder().getAbsolutePath() + "/zones.db");
        
        this.getCommand("zone").setExecutor(new ZoneCommandExecutor());
        
        getLogger().info("Plugin enabled successfully!");
    }
    
    @Override
    public void onDisable() {
        if (zoneDatabase != null) {
            zoneDatabase.close();
        }
        
        getLogger().info("Plugin disabled.");
    }
    
    public static ZoneManagers getInstance() {
        return instance;
    }
}
