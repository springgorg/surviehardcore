package fr.springg.surviehardcore.holograms;

import java.util.ArrayList;
import java.util.List;

public class HologramManager {

    public List<Hologram> holograms;

    public HologramManager(){
        this.holograms = new ArrayList<>();
    }

    public List<Hologram> getHolograms() {
        return holograms;
    }
}
