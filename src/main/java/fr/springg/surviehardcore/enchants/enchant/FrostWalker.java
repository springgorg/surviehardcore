package fr.springg.surviehardcore.enchants.enchant;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FrostWalker extends Enchantment implements Listener {

    public FrostWalker(int id) {
        super(id);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location loc= p.getLocation().add(0,-1,0);

        if((loc.getBlock().getType().equals(Material.STATIONARY_WATER) || loc.getBlock().getType().equals(Material.WATER)) && p.getEquipment().getBoots().containsEnchantment(this)){
            loc.getBlock().setType(Material.ICE);
        } else {
            return;
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
                if (Math.random() < 0.15) {
                    IInventory iInventory = ((CraftInventory) e.getClickedBlock()).getInventory();
                    ContainerEnchantTable cet = new ContainerEnchantTable(
                            ((PlayerInventory) ((CraftInventory) e.getPlayer().getInventory()).getInventory()),
                            (net.minecraft.server.v1_8_R3.World) e.getPlayer().getWorld(),
                            new BlockPosition(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())
                    );
                    int offer = new Random().nextInt(3);
                    cet.h[offer] = this.getId();
                    cet.a(iInventory);
                }
            }
        }
    }

    @Override
    public int getId(){
        return 101;
    }

    @Override
    public String getName() {
        return "Frost Walker";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_FEET;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return true;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

}
