package fr.springg.surviehardcore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class CancelsListener implements Listener {

    @EventHandler
    public void onAnvil(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.AIR) return;

        if (e.getInventory().getType() == InventoryType.ANVIL) {
            if(e.getSlotType() == InventoryType.SlotType.RESULT) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Kamui")
                        || e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Sharingan")
                        || e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Tsukuyomi")) {
                    e.setCancelled(true);
                    p.sendMessage("§cNon tu n'as pas le droit !");
                }
            }
        }
    }

}
