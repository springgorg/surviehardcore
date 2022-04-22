package fr.springg.surviehardcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fr.springg.surviehardcore.managers.Managers;
import fr.springg.surviehardcore.utils.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private static Main instance;
    public ProtocolManager protocolManager;
    public final Map<String, FastBoard> boards = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        Bukkit.getConsoleSender().sendMessage("§a{SURVIE HARDCORE]");

        // Managers
        Managers.registerCommands();
        Managers.registerListeners();

        // Scoreboard
        getServer().getScheduler().runTaskTimer(this, () -> {
            for(FastBoard board : boards.values()){
                updateBoard(board);
            }
        }, 0, 20);
    }

    private void updateBoard(FastBoard board){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        board.updateLines("§8" + dateFormat.format(date),
                "",
                "§aMap§7: §f§l" + board.getPlayer().getWorld(),
                "§aJoueurs§7: §f§l" + Bukkit.getOnlinePlayers().size(),
                "",
                "§aMode§7: §f§lSurvie HardCore KEKW",
                "",
                "§6onii-san.minesr.com"
        );
    }

    public static Main getInstance() {
        return instance;
    }
}
