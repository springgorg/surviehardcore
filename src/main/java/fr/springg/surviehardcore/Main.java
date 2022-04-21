package fr.springg.surviehardcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fr.springg.surviehardcore.managers.Managers;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    public ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        Bukkit.getConsoleSender().sendMessage("§a{SURVIE HARDCORE]");

        Managers.registerCommands();
        Managers.registerListeners();
    }

    public static Main getInstance() {
        return instance;
    }
}
