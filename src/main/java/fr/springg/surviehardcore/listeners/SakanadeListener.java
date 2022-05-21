package fr.springg.surviehardcore.listeners;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SakanadeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack it = e.getItem();
        Action a = e.getAction();

        if(a == null) return;
        if(it == null||it.getType().equals(Material.AIR)) return;

        if(a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (it.getType() == Material.IRON_SWORD && it.getItemMeta().getDisplayName().equalsIgnoreCase("§5Sakanade")) {
                Location center = p.getLocation().getBlock().getLocation().clone().add(0.5, 1, 0.5);

                new BukkitRunnable() {

                    float angle = 0f;
                    final double RADIUS = 1.3;

                    ArmorStand armorStand = null;

                    @Override
                    public void run() {

                        double x = RADIUS * Math.sin(angle);
                        double y = RADIUS * Math.cos(angle);

                        if(armorStand == null){
                            armorStand = center.getWorld().spawn(center.clone().add(x, y, 0),  ArmorStand.class);
                            armorStand.setVisible(false);
                            armorStand.setHelmet(new ItemBuilder(Material.DIAMOND_BLOCK).toItemStack());
                            armorStand.setSmall(true);
                            armorStand.setGravity(false);
                        }

                        angle += 0.1;

                        armorStand.teleport(center.clone().add(x, y, 0));
                    }
                }.runTaskTimer(Main.getInstance(), 0, 1);
            }
        }
    }

}
