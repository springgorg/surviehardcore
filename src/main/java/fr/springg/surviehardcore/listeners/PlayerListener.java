package fr.springg.surviehardcore.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.FastBoard;
import fr.springg.surviehardcore.utils.ItemBuilder;
import fr.springg.surviehardcore.utils.Title;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        Location loc = new Location(Bukkit.getWorld("Japan-with-barrier"), -39.440, 18.000, -20.406, -44, 4);

        //String url = "https://download2283.mediafire.com/ogloraglvcug/4ish30osos8wi4j/%21+++++++++++++++++++++++++++++++++++++++++++++++++++++++++%C2%A7cYo+soy+racisto+donde+esta.zip";
        /*String hash = "d168c2f9ac87e066d58bb841e0d9e3afeeefb1ff";

        ((CraftPlayer) p).getHandle().setResourcePack(url, hash);

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.RESOURCE_PACK_SEND);
        packet.getStrings().write(0, url);
        packet.getStrings().write(1, hash);
        try {
            Main.getInstance().protocolManager.sendServerPacket(p, packet);
        } catch (InvocationTargetException | NullPointerException ex){
            ex.printStackTrace();
        }*/

        p.setResourcePack("https://www.dropbox.com/s/xyhlq4ayrcad3bf/%21%20%20%20%20%20%20%20%20%20%20%20%C2%A7aJe%20suis%20raciste.zip?dl=0");
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

        Bukkit.getOnlinePlayers().forEach(pls -> pls.playSound(pls.getLocation(), Sound.ORB_PICKUP, 1,1));
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
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        e.setDeathMessage("§cLe joueur §7" + p.getName() + " §ca été tué par §r" + p.getKiller().getName() + "§c !");

        if(p.getWorld().getName().equalsIgnoreCase("world")) {
            ItemStack[] items = p.getInventory().getContents();
            Location loc = p.getLocation();
            Block block = loc.getBlock();
            loc.getBlock().setType(Material.CHEST);
            Chest chest = (Chest)block.getState();
            Inventory inv = chest.getInventory();
            inv.addItem(items);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Location loc = new Location(Bukkit.getWorld("Japan-with-barrier"), -39.440, 18.000, -20.406, -44, 4);
        Player p = e.getPlayer();
        e.setRespawnLocation(loc);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();

            if(p.getWorld().getName().equalsIgnoreCase("Japan-with-barrier") || p.getWorld().getName().equalsIgnoreCase("spawnhorror")){
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
            if(p.getWorld().getName().equalsIgnoreCase("Japan-with-barrier") || p.getWorld().getName().equalsIgnoreCase("spawnhorror") && (!p.isOp())){
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(p.getWorld().getName().equalsIgnoreCase("Japan-with-barrier") || p.getWorld().getName().equalsIgnoreCase("spawnhorror") && (!p.isOp())){
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(p.getWorld().getName().equalsIgnoreCase("Japan-with-barrier") || p.getWorld().getName().equalsIgnoreCase("spawnhorror") && (!p.isOp())){
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

}
