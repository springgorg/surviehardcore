package fr.springg.surviehardcore.worlds;

import fr.springg.surviehardcore.utils.Schematic;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class CustomPopulator extends BlockPopulator {
    @Override
    public void populate(World w, Random r, Chunk chunk) {
        if(r.nextBoolean()) {
            int amount = r.nextInt(4) + 1;
            for (int i = 0; i < amount; i++) {
                int x = r.nextInt(500);
                int z = r.nextInt(500);
                Schematic.loadSchematic(new Location(w, x, 0, z), "backroom");
            }
        }
    }
}
