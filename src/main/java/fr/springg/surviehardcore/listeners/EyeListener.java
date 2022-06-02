package fr.springg.surviehardcore.listeners;

import fr.springg.surviehardcore.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EyeListener implements Listener {

    Map<String, Location> previousLoc = new HashMap<>();

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
                        pls.teleport(new Location(Bukkit.getWorld("kamui"), 0.540, 23.000, 0.474, 90.4f, 0.5f));
                        p.teleport(new Location(Bukkit.getWorld("kamui"), 0.540, 23.000, 0.474, 90.4f, 0.5f));
                        pls.playSound(pls.getLocation(), Sound.PORTAL_TRAVEL, 1,2);
                        p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1,2);
                        Title title = new Title("§cKamui !","",20,100,20);
                        title.send(p);
                        title.send(pls);
                        previousLoc.put(p.getName(), p.getLocation());
                    }
                });
            } else if(it.getType().equals(Material.CLAY_BALL)
                    && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cKamui")
                    && p.isSneaking()
                    && p.getWorld().getName().equalsIgnoreCase("kamui")){
                p.teleport(previousLoc.get(p.getName()));
                previousLoc.remove(p.getName());
                p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1,2);
                Title title = new Title("§cKamui !","",20,100,20);
                title.send(p);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(p.getWorld().getName().equalsIgnoreCase("kamui")){
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
        }
    }

}
