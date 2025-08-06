package com.endmceval.commands;

import com.endmceval.core.ZoneManagers;
import com.endmceval.gui.ZoneGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZoneCommandExecutor implements CommandExecutor {
    
    private final ZoneGUI zoneGUI;
    
    public ZoneCommandExecutor() {
        this.zoneGUI = new ZoneGUI(ZoneManagers.zoneManager);
        this.loadInitialZones();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ZoneManagers.messageManager.get("no_permission"));
            return true;
        }
        
        Player player = (Player) sender;
        
        if (!player.hasPermission("zonemanager.admin")) {
            player.sendMessage(ZoneManagers.messageManager.get("no_permission"));
            return true;
        }
        
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            this.handleReload(player);
            return true;
        }
        
        this.zoneGUI.openZoneList(player);
        return true;
    }
    
    private void handleReload(Player player) {
        player.sendMessage("§eReloading zones...");
        
        ZoneManagers.zoneDatabase.loadZonesAsync()
            .thenAccept(zones -> {
                ZoneManagers.zoneManager.loadZones(zones);
                player.sendMessage(ZoneManagers.messageManager.get("reload_success"));
            })
            .exceptionally(throwable -> {
                player.sendMessage("§cError occurred while reloading zones: " + throwable.getMessage());
                ZoneManagers.getInstance().getLogger().severe("Failed to reload zones: " + throwable.getMessage());
                return null;
            });
    }
    
    private void loadInitialZones() {
        ZoneManagers.zoneDatabase.loadZonesAsync()
            .thenAccept(zones -> ZoneManagers.zoneManager.loadZones(zones))
            .exceptionally(throwable -> {
                ZoneManagers.getInstance().getLogger().severe("Failed to load initial zones: " + throwable.getMessage());
                return null;
            });
    }
}
