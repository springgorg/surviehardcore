package fr.springg.surviehardcore.backrooms;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MonsterStage0 implements Listener {

    public void custom(Location loc){
        Enderman enderman = Bukkit.getWorld(loc.getWorld().getName()).spawn(loc, Enderman.class);

        enderman.setHealth(2000);
        enderman.setMaxHealth(2000);

        enderman.setCustomName("§0§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        enderman.setCustomNameVisible(true);

        enderman.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999,2));

        Bukkit.getOnlinePlayers().forEach(enderman::setTarget);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Enderman){
            if(e.getDamager().getName().equals("§0§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")){
                if(e.getEntity() instanceof Player){
                    Player p = (Player) e.getEntity();
                    p.setHealth(0.0);
                }
            }
        }
    }

}
