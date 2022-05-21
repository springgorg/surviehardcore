package fr.springg.surviehardcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EyeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();

        if(a == null || it == null) return;
        if(it.getType().equals(Material.AIR)) return;

        if(a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (it.getType().equals(Material.CLAY_BALL) && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cKamui")) {
                p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3).forEach(ent -> {
                    if (ent instanceof Player) {
                        // teleport
                        Player pls = (Player) ent;
                        pls.teleport(new Location(Bukkit.getWorld("kamui"), 0, 1, 0));
                    }
                });
            }
        }
    }

}
