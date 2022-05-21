package fr.springg.surviehardcore.listeners;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.invs.BackroomsInv;
import fr.springg.surviehardcore.invs.NPCInv;
import fr.springg.surviehardcore.npc.MenuTrait;
import fr.springg.surviehardcore.npc.NPCTrait;
import fr.springg.surviehardcore.utils.Schematic;
import fr.springg.surviehardcore.utils.WorldUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensDisableEvent;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onCitizensEnable(CitizensEnableEvent e){
        NPC menu = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Gramiraax");
        menu.addTrait(MenuTrait.class);
        menu.spawn(new Location(Bukkit.getWorld("Japan-with-barrier"), -30.609, 17.000, -11.607, 136, 0));


        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Horror");
        npc.addTrait(NPCTrait.class);
        npc.spawn(new Location(Bukkit.getWorld("spawnhorror"),-4.700,5.00,0.528,-90,1));
    }

    @EventHandler
    public void onCitizensDisable(CitizensDisableEvent e){
        CitizensAPI.getNPCRegistries().forEach(NPCRegistry::deregisterAll);
        Bukkit.getConsoleSender().sendMessage("§c[SURVIE HARDCORE] SUPPRESSION DES NPC !");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();

        if(a == null || it == null) return;
        if(!it.hasItemMeta()) return;
        if((a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK))&&it.getItemMeta().getDisplayName().equalsIgnoreCase("§b§lLe baton cursed")){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                p.playSound(p.getLocation(), Sound.WITHER_SHOOT,1,1);
                p.launchProjectile(WitherSkull.class).setVelocity(p.getLocation().getDirection());
            }, 20);
        } else if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§0§lNigga stick")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
                p.launchProjectile(EnderPearl.class).setVelocity(p.getLocation().getDirection());
            }, 20);

        }
    }

    @EventHandler
    public void onInteractNPC(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        if(e.getRightClicked() instanceof NPC){
            NPC npc = (NPC)e.getRightClicked();
            if(npc.getName().equalsIgnoreCase("Horror")){
                p.playSound(p.getLocation(), Sound.ENDERMAN_DEATH,2,0);
                new BackroomsInv().open(p);
            }
        }
    }

}
