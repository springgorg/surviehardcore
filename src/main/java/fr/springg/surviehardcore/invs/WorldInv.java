package fr.springg.surviehardcore.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class WorldInv extends FastInv {
    public WorldInv() {
        super(6*9, "§c§lWorlds");

        Bukkit.getWorlds().forEach(w -> {
            addItem(new ItemBuilder(Material.GRASS).setName(w.getName()).toItemStack());
        });
    }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(it == null)return;

        if(e.getInventory().getName().equalsIgnoreCase("§c§lWorlds")){

            if(it.getType().equals(Material.GRASS)){
                World w = Bukkit.getWorld(it.getItemMeta().getDisplayName());
                Location l = new Location(w,w.getSpawnLocation().getX(),w.getSpawnLocation().getY(),w.getSpawnLocation().getZ());
                if(l.getBlock().getRelative(BlockFace.DOWN).isEmpty()){
                    p.teleport(new Location(w,0,1,0));
                    p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
                    p.closeInventory();
                } else {
                    p.teleport(l);
                    p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
                    p.closeInventory();
                }
            }

        }

        super.onClick(e);
    }
}
