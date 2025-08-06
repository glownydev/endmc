package com.endmceval.gui;

import com.endmceval.core.ZoneManagers;
import com.endmceval.managers.ZoneManager;
import com.endmceval.models.Zone;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ZoneGUI implements Listener {
    
    private final ZoneManager zoneManager;
    private static final String ZONE_GUI_TITLE = "Zone Manager";
    private static final int GUI_SIZE = 54;
    
    public ZoneGUI(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
        Bukkit.getPluginManager().registerEvents(this, ZoneManagers.getInstance());
    }
    
    public void openZoneList(Player player) {
        List<Zone> zones = this.zoneManager.getZones();
        Inventory inventory = Bukkit.createInventory(null, GUI_SIZE, ZONE_GUI_TITLE);
        
        int slot = 0;
        for (Zone zone : zones) {
            if (slot >= GUI_SIZE - 9) break; // Leave bottom row for navigation
            
            ItemStack item = createZoneItem(zone);
            inventory.setItem(slot, item);
            slot++;
        }
        
        // Add info item at bottom
        ItemStack infoItem = new ItemStack(Material.BOOK);
        ItemMeta infoMeta = infoItem.getItemMeta();
        if (infoMeta != null) {
            infoMeta.setDisplayName(ChatColor.GOLD + "Zone Information");
            infoMeta.setLore(Arrays.asList(
                ChatColor.GRAY + "Total zones: " + zones.size(),
                ChatColor.GRAY + "Click a zone to view details"
            ));
            infoItem.setItemMeta(infoMeta);
        }
        inventory.setItem(GUI_SIZE - 5, infoItem);
        
        player.openInventory(inventory);
    }
    
    private ItemStack createZoneItem(Zone zone) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        
        if (meta != null) {
            meta.setDisplayName(ChatColor.GREEN + zone.getName());
            meta.setLore(Arrays.asList(
                ChatColor.GRAY + "ID: " + zone.getId(),
                ChatColor.GRAY + "Priority: " + zone.getPriority(),
                ChatColor.GRAY + "Flags: " + zone.getFlags().size(),
                "",
                ChatColor.YELLOW + "Click to view details"
            ));
            item.setItemMeta(meta);
        }
        
        return item;
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!ZONE_GUI_TITLE.equals(event.getView().getTitle())) {
            return;
        }
        
        event.setCancelled(true);
        
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getWhoClicked();
        int slot = event.getRawSlot();
        
        if (slot < 0 || slot >= GUI_SIZE) {
            return;
        }
        
        List<Zone> zones = this.zoneManager.getZones();
        
        if (slot < zones.size()) {
            Zone zone = zones.get(slot);
            this.handleZoneClick(player, zone);
        }
    }
    
    private void handleZoneClick(Player player, Zone zone) {
        if (!player.hasPermission("zonemanager.view")) {
            player.sendMessage(ZoneManagers.messageManager.get("gui_no_permission"));
            return;
        }
        
        player.closeInventory();
        
        player.sendMessage(ChatColor.GREEN + "=== Zone: " + zone.getName() + " ===");
        player.sendMessage(ChatColor.GRAY + "ID: " + ChatColor.WHITE + zone.getId());
        player.sendMessage(ChatColor.GRAY + "Priority: " + ChatColor.WHITE + zone.getPriority());
        player.sendMessage(ChatColor.GRAY + "Flags:");
        
        if (zone.getFlags().isEmpty()) {
            player.sendMessage(ChatColor.GRAY + "  No flags set");
        } else {
            zone.getFlags().forEach((key, value) -> 
                player.sendMessage(ChatColor.GRAY + "  " + key + ": " + ChatColor.WHITE + value)
            );
        }
    }
}
