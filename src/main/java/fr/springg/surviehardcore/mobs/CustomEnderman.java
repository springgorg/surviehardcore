package fr.springg.surviehardcore.mobs;

import fr.springg.surviehardcore.utils.Attributes;
import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomEnderman implements Listener {

    public void custom(Player p) {
        Enderman ender = p.getLocation().getWorld().spawn(p.getLocation(), Enderman.class);

        ender.setCustomName("§0§lSlenderman");
        ender.setCustomNameVisible(true);

        ender.setMaxHealth(300);
        ender.setHealth(300);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Enderman){
            if(e.getDamager().getName().equals("§0§lSlenderman")){
                if(e.getEntity() instanceof Player){
                    Player p = (Player) e.getEntity();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,255*20,255));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 255*20,1));
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof Enderman && e.getEntity().getCustomName().equalsIgnoreCase("§0§lSlenderman")){
            e.getDrops().clear();
            e.getDrops().add(new ItemBuilder(Material.STICK).setName("§0§lNigga stick").toItemStack());
        }
    }

}
