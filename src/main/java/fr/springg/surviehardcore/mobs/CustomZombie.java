package fr.springg.surviehardcore.mobs;

import fr.springg.surviehardcore.Main;
import fr.springg.partencouille.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomZombie {

    public void custom(Player p){
        Zombie z = p.getLocation().getWorld().spawn(p.getLocation(), Zombie.class);
        z.setBaby(true);
        z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 2));

        z.setMaxHealth(200);
        z.setHealth(200);

        z.setCustomNameVisible(true);
        z.setCustomName("§c§lDobby");

        z.getEquipment().setHelmet(new ItemStack(Material.SKULL_ITEM));
        z.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.BLACK).toItemStack());
        z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
        z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
    }

}
