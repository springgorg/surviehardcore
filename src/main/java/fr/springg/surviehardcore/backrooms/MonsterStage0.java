package fr.springg.surviehardcore.backrooms;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class MonsterStage0 implements Listener {

    public static List<Player> monsterTouch = new ArrayList<>();

    public void custom(Location loc){
        Enderman enderman = Bukkit.getWorld(loc.getWorld().getName()).spawn(loc, Enderman.class);

        enderman.setHealth(2000);
        enderman.setMaxHealth(2000);

        enderman.setCustomName("§0§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        enderman.setCustomNameVisible(true);

        enderman.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999,3));

        Bukkit.getOnlinePlayers().forEach(enderman::setTarget);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Enderman){
            if(e.getDamager().getName().equals("§0§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")){
                if(e.getEntity() instanceof Player){
                    Player p = (Player) e.getEntity();
                    monsterTouch.add(p);
                    if(monsterTouch.contains(p)) {
                        p.setHealth(0.0);
                        p.playSound(p.getLocation(), Sound.GHAST_DEATH,2,0);
                        monsterTouch.remove(p);
                    }
                }
            }
        }
    }

}
