package fr.springg.surviehardcore.managers;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.backrooms.MonsterStage0;
import fr.springg.surviehardcore.commands.*;
import fr.springg.surviehardcore.enchants.enchant.FrostWalker;
import fr.springg.surviehardcore.listeners.*;
import fr.springg.surviehardcore.mobs.CustomCreeper;
import fr.springg.surviehardcore.mobs.CustomEnderman;
import fr.springg.surviehardcore.mobs.CustomSkeleton;
import fr.springg.surviehardcore.mobs.CustomZombie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;


public class Managers {

    private static Main main = Main.getInstance();

    public static void registerCommands(){
        main.getCommand("custom").setExecutor(new Commands());
        main.getCommand("kit").setExecutor(new CommandKit());
        main.getCommand("worlds").setExecutor(new CommandWorld());
        main.getCommand("customnpc").setExecutor(new CommandNPC());
        main.getCommand("menu").setExecutor(new CommandMenu());
        main.getCommand("hub").setExecutor(new CommandHub());
        main.getCommand("hologram").setExecutor(new CommandHologram());
        main.getCommand("map").setExecutor(new CommandMap());
    }

    public static void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        // Custom Mobs
        pm.registerEvents(new CustomZombie(), main);
        pm.registerEvents(new CustomSkeleton(), main);
        pm.registerEvents(new CustomEnderman(), main);
        pm.registerEvents(new CustomCreeper(), main);

        // Backrooms Mobs
        pm.registerEvents(new MonsterStage0(), main);

        // Players Events
        pm.registerEvents(new EyeListener(), main);
        pm.registerEvents(new PlayerInteract(), main);
        pm.registerEvents(new PlayerListener(), main);
        pm.registerEvents(new HammerListener(), main);
        pm.registerEvents(new CancelsListener(), main);
        pm.registerEvents(new SakanadeListener(), main);
        pm.registerEvents(new GenryusaiListener(), main);
        pm.registerEvents(new SakashimaListener(), main);
        pm.registerEvents(new ShinraListener(), main);

        // Enchantments
        pm.registerEvents(new FrostWalker(101), main);
    }

}
