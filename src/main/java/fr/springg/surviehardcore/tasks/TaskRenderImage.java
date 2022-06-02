package fr.springg.surviehardcore.tasks;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.maps.ImageMap;
import fr.springg.surviehardcore.maps.ImageMapRenderer;
import fr.springg.surviehardcore.maps.ImageMapYML;
import fr.springg.surviehardcore.utils.ImageHelper;
import fr.springg.surviehardcore.utils.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class TaskRenderImage extends BukkitRunnable {
    private Player p;
    private String path;

    public TaskRenderImage(Player p, String path){
        this.p = p;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            final ArrayList<Short> mapsIds = new ArrayList<>();
            final BufferedImage image = ImageHelper.getImage(path);
            final int row = image.getHeight() / 128;
            final int cols = image.getWidth() / 128;

            MapView map;
            for(int i = 0; i < row; i++){
                for(int j = 0; j < cols; j++){
                    map = Bukkit.createMap(p.getWorld());
                    map = RenderHelper.resetRenderers(map);
                    map.setScale(MapView.Scale.FARTHEST);
                    map.addRenderer(new ImageMapRenderer(image.getSubimage(j*128,i*128,128,128)));

                    mapsIds.add(map.getId());
                }
            }

            for(short id : mapsIds) {
                p.getInventory().addItem(new ItemStack(Material.MAP, 1, id));
                if(p.getInventory().getContents().length == 27){
                    p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.MAP,1,id));
                }
            }

            final ImageMap imageMap = new ImageMap(UUID.randomUUID(), path, mapsIds);
            final ImageMapYML imageMapYML = new ImageMapYML(imageMap.getUuid());

            imageMapYML.write(imageMap);
            Main.IMAGES_MAP_MANAGER.addImageMap(imageMap);
            p.sendMessage("§aRendu terminé !");

        } catch (IOException e) {
            Main.getInstance().getLogger().warning("Cannot load image "+ path+ ".");
            Main.getInstance().getLogger().warning(e.getMessage());
        }
    }

}
