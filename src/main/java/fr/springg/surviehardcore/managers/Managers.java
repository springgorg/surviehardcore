package fr.springg.surviehardcore.managers;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.commands.Commands;
import fr.springg.surviehardcore.listeners.PlayerInteract;
import fr.springg.surviehardcore.listeners.PlayerListener;
import fr.springg.surviehardcore.mobs.CustomSkeleton;
import fr.springg.surviehardcore.mobs.CustomZombie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Managers {

    public static void registerCommands(){
        Main.getInstance().getCommand("caca").setExecutor(new Commands());
        Main.getInstance().getCommand("pipi").setExecutor(new Commands());
    }

    public static void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        // Custom Mobs
        pm.registerEvents(new CustomZombie(), Main.getInstance());
        pm.registerEvents(new CustomSkeleton(), Main.getInstance());

        // Players Events
        pm.registerEvents(new PlayerInteract(), Main.getInstance());
        pm.registerEvents(new PlayerListener(), Main.getInstance());
    }

}
