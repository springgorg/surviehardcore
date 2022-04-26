package fr.springg.surviehardcore.mobs;

import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class CustomSkeleton implements Listener {

    public void custom(Player p){
        Skeleton s = p.getLocation().getWorld().spawn(p.getLocation(), Skeleton.class);

        s.setSkeletonType(Skeleton.SkeletonType.NORMAL);

        s.setMaxHealth(50);
        s.setHealth(50);

        s.setCustomName("§b§lLes Brooks");
        s.setCustomNameVisible(true);

        s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
        s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
        s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
        s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof Skeleton && e.getEntity().getCustomName().equalsIgnoreCase("§b§lLes Brooks")){
            e.getDrops().clear();
            Random r = new Random();
            int chance = r.nextInt();
            if(chance < 20) {
                e.getDrops().add(new ItemBuilder(Material.STICK).setName("§b§lLe baton cursed").toItemStack());
            }
        }
    }

    @EventHandler
    public void onThrow(EntityShootBowEvent e){
        if(e.getEntity() instanceof Skeleton){
            if(e.getEntity().getCustomName().equalsIgnoreCase("§b§lLes Brooks")){
                e.setCancelled(true);
                e.getEntity().launchProjectile(WitherSkull.class);
                // Régler le bug des directions
            }
        }
    }

}
