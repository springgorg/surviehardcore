package fr.springg.surviehardcore.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.surviehardcore.utils.ItemBuilder;
import fr.springg.surviehardcore.utils.Title;
import org.bukkit.*;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NPCInv extends FastInv {
    public NPCInv() {
        super(6*9, "§cMenu");

        // GLASSES
        for (int i = 0; i < 9; i++) {
            setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        }

        for (int i = 45; i < 54; i++) {
            setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        }

        setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());
        setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack());

        // ITEMS INV
        setItem(13, new ItemBuilder(Material.BEDROCK).setName("§0§l§kkkkkkk").toItemStack());
    }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(it == null) return;

        if(e.getInventory().getName().equalsIgnoreCase("§cMenu")){

            switch(it.getType()){

                case BEDROCK:
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5*20,10));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5*20,10));
                    new Title("§0§l§kkkkkkkkkkkkkkkkkkkkkk", "§8La curiosité est un vilain défaut !", 20,100,20).send(p);
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.sendMessage("§4§l§kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    p.setGameMode(GameMode.ADVENTURE);
                    p.teleport(new Location(Bukkit.getWorld("spawnhorror"), 3.619,1.875,0.489, 90, 1));
                    p.playSound(p.getLocation(), Sound.DONKEY_DEATH,2,0);
                    p.closeInventory();
                    break;

                default:
                    break;

            }

        }

        super.onClick(e);
    }
}
