package fr.springg.surviehardcore.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BackroomsInv extends FastInv {
    public BackroomsInv() {
        super(9, "§c§lBackrooms");

        for(int i = 0; i < 9;i++){
            setItem(i, new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)15));
        }

        setItem(4, new ItemBuilder(Material.RED_SANDSTONE).setName("§4§lBACKROOMS").setLore("","§c§lA vos risques et périls !","§c§lVous allez regretter !").toItemStack());
    }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(it == null) return;

        if(e.getInventory().getName().equalsIgnoreCase("§c§lBackrooms")){

            switch(it.getType()){

                case RED_SANDSTONE:
                    p.playSound(p.getLocation(), Sound.HORSE_DEATH,2,0);
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.closeInventory();
                    break;

                default:
                    break;

            }

        }

        super.onClick(e);
    }
}
