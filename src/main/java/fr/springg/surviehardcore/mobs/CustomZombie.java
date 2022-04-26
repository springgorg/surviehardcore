package fr.springg.surviehardcore.mobs;

import com.comphenix.protocol.wrappers.WrappedAttributeModifier;
import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.Attributes;
import fr.springg.surviehardcore.utils.ItemBuilder;
import net.minecraft.server.v1_8_R3.AttributeModifier;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class CustomZombie implements Listener {

    public void custom(Player p){
        Zombie z = p.getLocation().getWorld().spawn(p.getLocation(), Zombie.class);
        z.setBaby(true);
        z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 2));

        z.setMaxHealth(200);
        z.setHealth(200);

        z.setVillager(false);

        z.setCustomNameVisible(true);
        z.setCustomName("§c§lDobby");

        z.getEquipment().setHelmet(new ItemStack(Material.SKULL_ITEM));
        z.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.BLACK).toItemStack());
        z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
        z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof Zombie && e.getEntity().getCustomName().equalsIgnoreCase("§c§lDobby")){
            e.getDrops().clear();
            ItemStack it = Attributes.addAttributes(new ItemBuilder(Material.GOLD_BOOTS).setName("§c§lLes bottes cursed").toItemStack());
            e.getDrops().add(it);
        }
    }

}
