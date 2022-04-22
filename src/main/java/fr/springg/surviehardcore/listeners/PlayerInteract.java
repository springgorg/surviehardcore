package fr.springg.surviehardcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();

        if(a == null || it == null) return;
        if(a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)){
            if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§b§lLe baton cursed")){
                p.launchProjectile(WitherSkull.class).setVelocity(p.getLocation().getDirection());
            }
        }
    }

}
