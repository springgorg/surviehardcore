package fr.springg.surviehardcore.mobs;

import fr.springg.surviehardcore.Main;
import org.bukkit.*;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomCreeper implements Listener {

    public void custom(Player p){
        Creeper c = p.getLocation().getWorld().spawn(p.getLocation(), Creeper.class);

        c.setPowered(false);

        c.setMaxHealth(2000);
        c.setHealth(2000);

        c.setCustomName("§8§lBlack Catcher");
        c.setCustomNameVisible(true);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof Creeper && e.getEntity().getCustomName().equalsIgnoreCase("§8§lBlack Catcher")){
            e.getDrops().clear();
            e.getEntity().getLocation().getWorld().strikeLightning(e.getEntity().getLocation());
            e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.WITHER_DEATH,1,2);
            e.getEntity().getLocation().getWorld().createExplosion(e.getEntity().getLocation(), 200);
        }
    }

}
