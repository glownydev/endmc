package com.endmceval;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class ZoneGUI implements Listener {
    private ZoneManager zoneManager;
    public ZoneGUI(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
        Bukkit.getPluginManager().registerEvents(this, ZoneManagers.getInstance());
    }
    public void openZoneList(Player player) {
        List<Zone> zones = zoneManager.getZones();
        Inventory inv = Bukkit.createInventory(null, 27, "Zones");
        int i = 0;
        for (Zone zone : zones) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(zone.getName());
            item.setItemMeta(meta);
            inv.setItem(i, item);
            i++;
        }
        player.openInventory(inv);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals("Zones")) return;
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        int slot = e.getRawSlot();
        List<Zone> zones = zoneManager.getZones();
        if (slot < zones.size()) {
            Zone zone = zones.get(slot);
            if (!player.hasPermission("zonemanager.edit")) {
                player.sendMessage(ZoneManagers.messageManager.get("gui_no_permission"));
                return;
            }
            player.sendMessage(ZoneManagers.messageManager.get("gui_flags").replace("%flags%", zone.getFlags().toString()));
        }
    }
} 