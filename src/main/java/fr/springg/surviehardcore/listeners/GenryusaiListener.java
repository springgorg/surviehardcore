package fr.springg.surviehardcore.listeners;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.particles.GenryusaiEffect;
import fr.springg.surviehardcore.utils.Title;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GenryusaiListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack it = e.getItem();
        Action a = e.getAction();

        if(a == null) return;
        if(it == null || it.getType().equals(Material.AIR))return;

        if(a.equals(Action.RIGHT_CLICK_BLOCK) || a.equals(Action.RIGHT_CLICK_AIR)){
            if(it.getType().equals(Material.DIAMOND_SWORD) && it.getItemMeta().getDisplayName().equalsIgnoreCase("§4§lGenryusai")){
                p.playSound(p.getLocation(), Sound.FIRE, 1,0);
                new Title("§4§lGENRYUSAI !", "", 20,100,20).send(p);
                new GenryusaiEffect().sendCircleParticle(p.getLocation(), EnumParticle.FLAME, 3, p);
                p.getNearbyEntities(20,20,20).forEach(ent -> {
                    ent.setFireTicks(10*20);
                });
            }
        }
    }

}
