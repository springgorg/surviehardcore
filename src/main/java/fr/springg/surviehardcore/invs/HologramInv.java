package fr.springg.surviehardcore.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class HologramInv extends FastInv {

    public HologramInv() {
        super(6*9, "§cListe d'hologrammes");

        Main.getInstance().hologramManager.getHolograms().forEach(hologram -> {
            addItem(new ItemBuilder(Material.SKULL_ITEM).setName(hologram.getName()).setLore("",
                    "§dx:"+hologram.getLoc().getX(),
                    "§dy:"+hologram.getLoc().getY(),
                    "§dz:"+hologram.getLoc().getZ()).toItemStack());
        });

    }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(it == null)return;

        if(e.getInventory().getName().equalsIgnoreCase("§cListe d'hologrammes")){

            if(it.getType().equals(Material.SKULL_ITEM)){
                p.closeInventory();
                e.setCancelled(true);
            }

        }

        super.onClick(e);
    }
}
