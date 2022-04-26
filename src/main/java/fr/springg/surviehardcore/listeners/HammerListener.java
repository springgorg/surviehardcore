package fr.springg.surviehardcore.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HammerListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        if(item != null && item.getType() == Material.DIAMOND_PICKAXE && item.getItemMeta().getDisplayName().equalsIgnoreCase("§cMarteau")){
            Block block = e.getBlock();
            List<Block> blocks = new ArrayList<>();
            BlockFace[] blockFaces = BlockFace.values();
            for (BlockFace blockFace : blockFaces) {
                blocks.add(block.getRelative(blockFace));
            }

            for (Block b : blocks) {
                if(b.getType().equals(Material.BEDROCK)){
                    e.setCancelled(true);
                } else {
                    b.breakNaturally(item);
                }
            }
        }
    }

}
