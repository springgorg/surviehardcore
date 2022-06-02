package fr.springg.surviehardcore.listeners;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.tasks.TaskSakashima;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class ShinraListener implements Listener {

    public final Map<String, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();

        if(a == null) return;
        if(it == null) return;
        if(it.getType() == Material.AIR) return;

        if(a.equals(Action.RIGHT_CLICK_BLOCK) || a.equals(Action.RIGHT_CLICK_AIR)){
            if(it.getType() == Material.CLAY_BALL && it.getItemMeta().getDisplayName().equalsIgnoreCase("§dShinra Tensei")){
                p.getNearbyEntities(30,5,30).forEach(ent -> {
                    LivingEntity entity = (LivingEntity) ent;
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25*20, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25*20, 2));
                    Vector vector = entity.getVelocity();
                    entity.setVelocity(vector.multiply(3));

                    List<Block> blocks = getNearbyBlocks(p.getLocation(), 50);
                    Random r = new Random();
                    Block block = blocks.get(r.nextInt(blocks.size()));
                    block.breakNaturally();

                    if(cooldowns.containsKey(p.getName())){
                        if(cooldowns.get(p.getName()) > System.currentTimeMillis()){
                            long timeleft = (cooldowns.get(p.getName())-System.currentTimeMillis()/1000);
                            p.sendMessage("§cTemps restant : §f" + timeleft + " secondes");
                        }
                    }

                    cooldowns.put(p.getName(), System.currentTimeMillis()+(3600*1000));

                    /*new BukkitRunnable(){

                        int count = 0;
                        Vector movement;
                        List<Block> blocks = getNearbyBlocks(p.getLocation(),50);
                        List<FallingBlock> fblocks = new ArrayList<>();

                        @Override
                        public void run() {
                            if(count == 300){
                                cancel();
                            }
                            Random r = new Random();
                            Block block = blocks.get(r.nextInt(blocks.size()));
                            FallingBlock fblock = (FallingBlock) p.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
                            fblock.setVelocity((fblock.getLocation().toVector().subtract(p.getLocation().toVector()).multiply(-5).normalize()));
                            fblock.setDropItem(false);
                            fblock.setHurtEntities(true);
                            count++;
                        }
                    }.runTaskTimer(Main.getInstance(),0,0);*/
                });
            }
        }
    }

    public List<Block> getNearbyBlocks(Location loc, int radius){
        List<Block> blocks = new ArrayList<>();
        for (int x = loc.getBlockX() - radius; x <= loc.getBlockX()+radius; x++) {
            for (int z = loc.getBlockZ() - radius; z <= loc.getBlockZ()+radius; z++) {
                blocks.add(loc.getWorld().getHighestBlockAt(x,z).getLocation().subtract(0,1,0).getBlock());
            }
        }
        return blocks;
    }

}
