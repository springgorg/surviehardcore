package fr.springg.surviehardcore.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.surviehardcore.enchants.enchant.FrostWalker;
import fr.springg.surviehardcore.mobs.CustomCreeper;
import fr.springg.surviehardcore.mobs.CustomEnderman;
import fr.springg.surviehardcore.mobs.CustomSkeleton;
import fr.springg.surviehardcore.mobs.CustomZombie;
import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CustomInv extends FastInv {
    public CustomInv() {
        super(6*9, "§c§lCustom Mobs");

        setItem(10, new ItemBuilder(Material.ROTTEN_FLESH).setName("§c§lDobby").toItemStack());
        setItem(13, new ItemBuilder(Material.BONE).setName("§b§lLes Brooks").toItemStack());
        setItem(16, new ItemBuilder(Material.ENDER_PEARL).setName("§0§lSlenderman").toItemStack());
        setItem(18, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§9§lFrost Walker").toItemStack());
        setItem(20, new ItemBuilder(Material.SULPHUR).setName("§8§lBlack Catcher").toItemStack());

    }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(it == null)return;

        if(e.getInventory().getName().equalsIgnoreCase("§c§lCustom Mobs")){

            switch(it.getType()){

                case ROTTEN_FLESH:
                    new CustomZombie().custom(p);
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1,1);
                    p.closeInventory();
                    break;

                case BONE:
                    new CustomSkeleton().custom(p);
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1,1);
                    p.closeInventory();
                    break;

                case ENDER_PEARL:
                    new CustomEnderman().custom(p);
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1,1);
                    p.closeInventory();
                    break;

                case DIAMOND_BOOTS:
                    FrostWalker fEnch = new FrostWalker(101);
                    p.getEquipment().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS).setLore("§b" + fEnch.getName() + " I").addUnsafeEnchantment(fEnch,1).toItemStack());
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1,1);
                    p.closeInventory();
                    break;

                case SULPHUR:
                    new CustomCreeper().custom(p);
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1,1);
                    p.closeInventory();
                    break;

            }

        }

        super.onClick(e);
    }
}
