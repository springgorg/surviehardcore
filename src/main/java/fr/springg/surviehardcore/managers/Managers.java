package fr.springg.surviehardcore.managers;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.commands.Commands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Managers {

    public static void registerCommands(){
        Main.getInstance().getCommand("caca").setExecutor(new Commands());
    }

    public static void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
    }

}
