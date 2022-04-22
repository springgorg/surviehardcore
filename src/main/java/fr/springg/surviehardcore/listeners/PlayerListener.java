package fr.springg.surviehardcore.listeners;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.FastBoard;
import fr.springg.surviehardcore.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage("§e" + p.getName() + "§a a rejoint le serveur !");

        FastBoard board = new FastBoard(p);
        board.updateTitle("§c§lSURVIE HARDCORE");
        Main.getInstance().boards.put(p.getName(), board);

        Title title = new Title("§f§lBienvenue !", "", 20, 100, 20);
        title.send(p);

        Bukkit.getOnlinePlayers().forEach(pls -> pls.playSound(p.getLocation(), Sound.ORB_PICKUP, 1,1));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage("§c" + p.getName() + "§c a quitté la partie !");

        FastBoard board = Main.getInstance().boards.remove(p.getName());
        if(board != null) board.delete();
    }

}
