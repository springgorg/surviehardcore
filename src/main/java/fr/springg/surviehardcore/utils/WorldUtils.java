package fr.springg.surviehardcore.utils;

import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;

public class WorldUtils {

    private static World world;

    public static void create(String name){
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("2;0;1;");
        worldCreator.generateStructures(false);
        world = worldCreator.createWorld();
    }

}
