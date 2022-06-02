package fr.springg.surviehardcore.utils;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.maps.ImageMap;
import fr.springg.surviehardcore.maps.ImageMapYML;
import fr.springg.surviehardcore.tasks.TaskUpdateImage;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataLoader {

    public static void loadMaps() throws IOException {
        final File imageDir = Main.IMAGES_DIR;
        final File imageMapDir = Main.IMAGES_MAP_DIR;

        if(!imageDir.exists()){
            if(!imageDir.mkdirs()){
                throw new IOException("Cannot create images directory.");
            }
        }

        if(!imageMapDir.exists()){
            if(!imageMapDir.mkdirs()){
                throw new IOException("Cannot create images maps directory.");
            }
        }

        final File[] files = imageMapDir.listFiles();

        if(files != null){
            ImageMap imageMap;
            ImageMapYML imageMapYML;

            for(File file : files){
                if(file.getName().endsWith(".yml")){
                    imageMapYML = new ImageMapYML(UUID.fromString(file.getName().replaceAll(".yml", "")));
                    imageMap = imageMapYML.read();

                    Main.IMAGES_MAP_MANAGER.addImageMap(imageMap);
                    new TaskUpdateImage(imageMap).runTaskAsynchronously(Main.getInstance());
                }
            }
        }
    }

}
