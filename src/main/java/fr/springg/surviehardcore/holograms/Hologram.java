package fr.springg.surviehardcore.holograms;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Hologram {

    private String name;
    private Location loc;

    public Hologram(String name, Location loc){
        this.name = name;
        this.loc = loc;
    }

    public void create(){
        ArmorStand stand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        stand.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        stand.setCustomNameVisible(true);
        stand.setVisible(false);
        stand.setGravity(false);
    }

    public Location getLoc() { return loc; }

    public String getName() { return name; }
}
