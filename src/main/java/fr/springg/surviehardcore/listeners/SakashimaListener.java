package fr.springg.surviehardcore.listeners;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.tasks.TaskSakashima;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class SakashimaListener implements Listener {

    Map<String, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();

        if(a == null) return;
        if(it == null) return;
        if(it.getType() == Material.AIR) return;

        if(a.equals(Action.RIGHT_CLICK_BLOCK) || a.equals(Action.RIGHT_CLICK_AIR)){
            if(it.getType() == Material.IRON_SWORD && it.getItemMeta().getDisplayName().equalsIgnoreCase("§5Sakashima Yokoshima Happofusagari")){

                if(cooldowns.containsKey(p.getName())){
                    if(cooldowns.get(p.getName()) > System.currentTimeMillis()){
                        long timeleft = (cooldowns.get(p.getName())-System.currentTimeMillis()/1000);
                        p.sendMessage("§cTemps restant : §f" + timeleft + " secondes");
                    }
                }

                cooldowns.put(p.getName(), System.currentTimeMillis()+(120*1000));

                p.getNearbyEntities(20,5,20).forEach(ent -> {
                    LivingEntity entity = (LivingEntity) ent;
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25*20, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25*20, 2));
                    new TaskSakashima(entity).runTaskTimer(Main.getInstance(), 0L, 20L);
                });
            }
        }
    }

}
