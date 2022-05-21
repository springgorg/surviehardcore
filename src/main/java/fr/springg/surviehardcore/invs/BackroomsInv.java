package fr.springg.surviehardcore.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.backrooms.BackroomsUtils;
import fr.springg.surviehardcore.backrooms.MonsterStage0;
import fr.springg.surviehardcore.utils.ItemBuilder;
import fr.springg.surviehardcore.utils.Title;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

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
                    new BukkitRunnable(){
                        private int timer = 5;
                        @Override
                        public void run() {
                            if(timer > 0){
                                double x = p.getLocation().getX();
                                double y = p.getLocation().getY();
                                double z = p.getLocation().getZ();
                                p.setGameMode(GameMode.SPECTATOR);
                                p.teleport(new Location(Bukkit.getWorld(p.getWorld().getName()), x, y-timer, z));
                            }
                            if(timer == 0){
                                p.closeInventory();
                                p.teleport(new Location(Bukkit.getWorld("tests"), -9.557, 1.00, 6.537, (float)-45.4, (float)0.4));
                                p.playSound(p.getLocation(), Sound.HORSE_DEATH,2,0);
                                Title title = new Title("§4§lBonne chance =)", "", 20,100,20);
                                title.send(p);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 9999*20, 1));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 9999*20, 2));
                                BackroomsUtils.backrooms.add(p);
                                Location loc = new Location(Bukkit.getWorld("tests"), 89,1,10);
                                MonsterStage0.custom(loc);
                            }
                            timer--;
                        }
                    }.runTaskTimer(Main.getInstance(),20,20);
                    break;

                default:
                    break;

            }

        }

        super.onClick(e);
    }
}
