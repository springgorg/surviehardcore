package fr.springg.surviehardcore.managers;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.commands.Commands;
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
        pm.registerEvents(new CustomZombie(), Main.getInstance());
        pm.registerEvents(new CustomSkeleton(), Main.getInstance());
    }

}
