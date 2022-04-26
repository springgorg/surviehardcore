package fr.springg.surviehardcore.managers;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.backrooms.MonsterStage0;
import fr.springg.surviehardcore.commands.*;
import fr.springg.surviehardcore.enchants.enchant.FrostWalker;
import fr.springg.surviehardcore.listeners.HammerListener;
import fr.springg.surviehardcore.listeners.PlayerInteract;
import fr.springg.surviehardcore.listeners.PlayerListener;
import fr.springg.surviehardcore.mobs.CustomCreeper;
import fr.springg.surviehardcore.mobs.CustomEnderman;
import fr.springg.surviehardcore.mobs.CustomSkeleton;
import fr.springg.surviehardcore.mobs.CustomZombie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Managers {

    public static void registerCommands(){
        Main.getInstance().getCommand("custom").setExecutor(new Commands());
        Main.getInstance().getCommand("kit").setExecutor(new CommandKit());
        Main.getInstance().getCommand("worlds").setExecutor(new CommandWorld());
        Main.getInstance().getCommand("customnpc").setExecutor(new CommandNPC());
        Main.getInstance().getCommand("menu").setExecutor(new CommandMenu());
        Main.getInstance().getCommand("hub").setExecutor(new CommandHub());
    }

    public static void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        // Custom Mobs
        pm.registerEvents(new CustomZombie(), Main.getInstance());
        pm.registerEvents(new CustomSkeleton(), Main.getInstance());
        pm.registerEvents(new CustomEnderman(), Main.getInstance());
        pm.registerEvents(new CustomCreeper(), Main.getInstance());

        // Backrooms Mobs
        pm.registerEvents(new MonsterStage0(), Main.getInstance());

        // Players Events
        pm.registerEvents(new PlayerInteract(), Main.getInstance());
        pm.registerEvents(new PlayerListener(), Main.getInstance());
        pm.registerEvents(new HammerListener(), Main.getInstance());

        // Enchantments
        pm.registerEvents(new FrostWalker(101), Main.getInstance());
    }

}
