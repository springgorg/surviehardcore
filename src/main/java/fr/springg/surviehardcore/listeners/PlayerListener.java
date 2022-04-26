package fr.springg.surviehardcore.listeners;

import com.comphenix.protocol.PacketType;
import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.FastBoard;
import fr.springg.surviehardcore.utils.Title;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        Location loc = new Location(Bukkit.getWorld("Japan-with-barrier"), -39.440, 18.000, -20.406, -44, 4);

        e.setJoinMessage("§e" + p.getName() + "§a a rejoint le serveur !");

        p.teleport(loc);
        p.setHealth(20.0);
        p.setFoodLevel(20);
        p.setExp(0);
        p.setLevel(0);
        p.getInventory().clear();
        p.getEquipment().clear();
        p.setGameMode(GameMode.ADVENTURE);
        p.setFlying(false);
        p.setAllowFlight(false);
        p.getActivePotionEffects().clear();

        FastBoard board = new FastBoard(p);
        board.updateTitle("§c§lSURVIE HARDCORE");
        Main.getInstance().boards.put(p.getName(), board);

        Title title = new Title("§f§lBienvenue !", "", 20, 100, 20);
        title.send(p);

        Bukkit.getOnlinePlayers().forEach(pls -> pls.playSound(p.getLocation(), Sound.ORB_PICKUP, 1,1));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        e.setFormat("§7§l%1$s §8: §f%2$s");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage("§c" + p.getName() + "§c a quitté la partie !");

        FastBoard board = Main.getInstance().boards.remove(p.getName());
        if(board != null) board.delete();
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        if(e.getEntity() instanceof Player){
            Player p =(Player) e.getEntity();

            if(p.getWorld().getName().equalsIgnoreCase("Japan-with-barrier")){
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(p.getWorld().getName().equalsIgnoreCase("Japan-with-barrier")){
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

}
