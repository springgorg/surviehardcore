package fr.springg.surviehardcore.backrooms;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class MonsterStage0 implements Listener {

    public static List<Player> monsterTouch = new ArrayList<>();

    public static void custom(Location loc) {
        Enderman enderman = loc.getWorld().spawn(loc, Enderman.class);

        enderman.setMaxHealth(99999);

        enderman.setHealth(99999);

        enderman.setCustomName("§0§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        enderman.setCustomNameVisible(true);
        enderman.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2));

        /*enderman.getNearbyEntities(3,3,3).forEach(ent -> {
            if(ent instanceof Player){
                Player nearby = (Player) ent;
                Title title = new Title("§c§l☺︎☜︎ ✞︎✌︎✋ ✞︎⚐︎︎ ❄︎ ︎☜︎☼︎","☺︎☜︎ ✞︎✌︎✋ ✞︎⚐︎︎ ❄︎ ︎☜︎☼",20,100,20);
                title.send(nearby);
                nearby.playSound(nearby.getLocation(), Sound.ENDERMAN_SCREAM,2,0);
            }
        });*/
    }

    @EventHandler
    public void onTeleport(EntityTeleportEvent e){
        if(e.getEntity() instanceof Enderman){
            Enderman enderman = (Enderman) e.getEntity();
            if(enderman.getCustomName().equalsIgnoreCase("§0§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Enderman){
            if(e.getDamager().getName().equals("§0§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")){
                if(e.getEntity() instanceof Player){
                    Player p = (Player) e.getEntity();
                    monsterTouch.add(p);
                    if(monsterTouch.contains(p)) {
                        p.setHealth(1.0);
                        monsterTouch.remove(p);
                    }

                    if(p.getHealth() == 1.0){
                        if(!BackroomsUtils.backrooms.contains(p)) return;
                        ((Enderman) e.getDamager()).setHealth(0.0);
                        p.setHealth(p.getMaxHealth());

                        BackroomsUtils.backrooms.remove(p);
                        p.teleport(new Location(Bukkit.getWorld("Japan-with-barrier"), -39.440, 18.000, -20.406, -44, 4));
                        p.playSound(p.getLocation(), Sound.GHAST_DEATH,2,0);
                        p.setFoodLevel(20);
                        p.setGameMode(GameMode.ADVENTURE);
                        p.removePotionEffect(PotionEffectType.SPEED);
                        p.removePotionEffect(PotionEffectType.SATURATION);
                        p.removePotionEffect(PotionEffectType.BLINDNESS);
                    }
                }
            }
        }
    }

}
