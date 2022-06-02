package fr.springg.surviehardcore.mobs;

import com.sk89q.worldedit.Vector;
import fr.springg.surviehardcore.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomCreeper implements Listener {

    public void custom(Player p){
        Creeper c = p.getLocation().getWorld().spawn(p.getLocation(), Creeper.class);

        c.setPowered(false);

        c.setMaxHealth(2000);
        c.setHealth(2000);

        c.setCustomName("§8§lBlack Catcher");
        c.setCustomNameVisible(true);
    }

    @EventHandler
    public void onExplode(EntityDeathEvent e){
        if(!(e.getEntity() instanceof Creeper)) return;
        Creeper creeper = (Creeper) e.getEntity();

        if(creeper.getCustomName().equalsIgnoreCase("§8§lBlack Catcher")) {

            Entity crystal = creeper.getWorld().spawnEntity(creeper.getLocation().add(0, 2, 0), EntityType.ENDER_CRYSTAL);

            new BukkitRunnable() {

                int count = 0;
                Vector movement;
                List<Block> blocks = getNearbyBlocks(creeper.getLocation(), 15);
                List<FallingBlock> fBlocks = new ArrayList<>();

                @Override
                public void run() {
                    if (count == 300) {
                        cancel();
                        crystal.remove();
                        TNTPrimed tnt = crystal.getWorld().spawn(crystal.getLocation(), TNTPrimed.class);
                        tnt.setFuseTicks(0);
                        return;
                    }
                    Random r = new Random();
                    Block block = blocks.get(r.nextInt(blocks.size() - 0) + 0);
                    FallingBlock fBlock = creeper.getWorld().spawnFallingBlock(block.getLocation(), block.getType().getId(), block.getData());
                    fBlock.setVelocity((fBlock.getLocation().toVector().subtract(crystal.getLocation().toVector()).multiply(-10).normalize()));
                    fBlock.setDropItem(false);
                    fBlock.setHurtEntities(true);
                    fBlocks.add(fBlock);

                    count++;
                }

            }.runTaskTimer(Main.getInstance(), 0, 0);
        }
    }

    public List<Block> getNearbyBlocks(Location loc, int radius){
        List<Block> blocks = new ArrayList<Block>();
        for(int x = loc.getBlockX() - radius; x <= loc.getBlockX()+radius;x++){
            for(int z = loc.getBlockZ() - radius; z <= loc.getBlockZ()+radius;z++){
                blocks.add(loc.getWorld().getHighestBlockAt(x,z));
            }
        }
        return blocks;
    }

}
