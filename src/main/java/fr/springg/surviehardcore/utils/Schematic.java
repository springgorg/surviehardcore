package fr.springg.surviehardcore.utils;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import com.sk89q.worldedit.world.World;
import fr.springg.surviehardcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

public class Schematic {

    public static void loadSchematic(Player p, String fileName) {
        Location loc = p.getLocation();
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 1000000);
        File schematic = new File(Main.getInstance().getDataFolder() + File.separator + "/schematics/" + fileName + ".schematic");
        try {

            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.rotate2D(90);
            clipboard.paste(session, new Vector(loc.getX(), loc.getY(), loc.getZ()), false);
        } catch (MaxChangedBlocksException | DataException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void loadSchematic(Location location, String fileName) {
        Location loc = location;
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 1000000);
        File schematic = new File(Main.getInstance().getDataFolder() + File.separator + "/schematics/" + fileName + ".schematic");
        try {

            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.rotate2D(90);
            clipboard.paste(session, new Vector(loc.getX(), loc.getY(), loc.getZ()), false);
        } catch (MaxChangedBlocksException | DataException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
