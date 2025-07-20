package com.endmceval;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MessageManager {
    private Map<String, String> messages = new HashMap<>();
    public MessageManager(JavaPlugin plugin) {
        File file = new File(plugin.getDataFolder(), "messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        for (String key : config.getKeys(false)) {
            messages.put(key, config.getString(key));
        }
    }
    public String get(String key) {
        return messages.getOrDefault(key, key);
    }
} 