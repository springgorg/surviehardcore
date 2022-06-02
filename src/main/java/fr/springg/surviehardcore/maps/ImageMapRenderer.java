package fr.springg.surviehardcore.maps;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.awt.image.BufferedImage;

public class ImageMapRenderer extends MapRenderer {

    private boolean shouldRender;
    private BufferedImage image;

    public ImageMapRenderer(BufferedImage image) {
        this.image = image;
        this.shouldRender = true;
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        if(shouldRender){
            canvas.drawImage(0,0,image);
            shouldRender = false;
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
    }
}
