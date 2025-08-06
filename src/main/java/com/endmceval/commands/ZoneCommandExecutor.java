package com.endmceval.commands;

import com.endmceval.core.ZoneManagers;
import com.endmceval.gui.ZoneGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZoneCommandExecutor implements CommandExecutor {
    private ZoneGUI zoneGUI;
    
    public ZoneCommandExecutor() {
        this.zoneGUI = new ZoneGUI(ZoneManagers.zoneManager);
        reloadZones();
    }
    
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
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reloadZones();
            player.sendMessage(ZoneManagers.messageManager.get("reload_success"));
            return true;
        }
        zoneGUI.openZoneList(player);
        return true;
    }
    
    private void reloadZones() {
        ZoneManagers.zoneDatabase.loadZonesAsync().thenAccept(zones -> {
            ZoneManagers.zoneManager.setZones(zones);
        });
    }
}
