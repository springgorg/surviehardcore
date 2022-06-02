package fr.springg.surviehardcore.maps;

import java.util.ArrayList;
import java.util.UUID;

public class ImageMap {

    private UUID uuid;
    private String path;
    private ArrayList<Short> mapsIds;

    public ImageMap(UUID uuid, String path, ArrayList<Short> mapsIds) {
        this.uuid = uuid;
        this.path = path;
        this.mapsIds = mapsIds;
    }

    public ArrayList<Short> getMapsIds() {
        return mapsIds;
    }

    public String getPath() {
        return path;
    }

    public UUID getUuid() {
        return uuid;
    }
}
