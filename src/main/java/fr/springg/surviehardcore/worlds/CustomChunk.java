package fr.springg.surviehardcore.worlds;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.*;

public class CustomChunk extends ChunkGenerator {

    int currentHeight=50;


    @Override
    public ChunkData generateChunkData(World w, Random r, int chunkX, int chunkZ, BiomeGrid b) {

        ChunkData chunk = createChunkData(w);
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(w.getSeed()), 8);
        generator.setScale(0.005D);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                currentHeight = (int) ((generator.noise(chunkX*16+x, chunkZ*16+z, 0.5D,0.5D, true)+1)*15D+50D);

                chunk.setBlock(x,0,z, Material.SANDSTONE);
                for(int i = currentHeight-2;i>0;i--) chunk.setBlock(x,i,z, Material.AIR);
                chunk.setBlock(x,5,z, 1,(byte)6);
            }
        }

        return chunk;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator)new CustomPopulator());
    }
}
