package fr.springg.surviehardcore.tasks;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.maps.ImageMap;
import fr.springg.surviehardcore.maps.ImageMapRenderer;
import fr.springg.surviehardcore.utils.ImageHelper;
import fr.springg.surviehardcore.utils.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class TaskUpdateImage extends BukkitRunnable {
    private ImageMap imageMap;

    public TaskUpdateImage(ImageMap imageMap){
        this.imageMap = imageMap;
    }

    @Override
    public void run() {
        try {
            final BufferedImage image = ImageHelper.getImage(imageMap.getPath());
            final int row = image.getHeight() / 128;
            final int cols = image.getWidth() / 128;

            MapView map;
            int index = 0;
            for(int i = 0; i < row; i++){
                for(int j = 0; j < cols; j++){
                    map = Bukkit.getMap(imageMap.getMapsIds().get(index));
                    map = RenderHelper.resetRenderers(map);
                    map.setScale(MapView.Scale.FARTHEST);
                    map.addRenderer(new ImageMapRenderer(image.getSubimage(j*128,i*128,128,128)));

                    index++;
                }
            }
        } catch (IOException e) {
            Main.getInstance().getLogger().warning("Cannot load image "+ imageMap.getPath() + ".");
            Main.getInstance().getLogger().warning(e.getMessage());
        }
    }

}
