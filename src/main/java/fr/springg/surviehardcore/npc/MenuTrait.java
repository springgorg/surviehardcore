package fr.springg.surviehardcore.npc;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.invs.BackroomsInv;
import fr.springg.surviehardcore.invs.NPCInv;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

@TraitName("MenuTrait")
public class MenuTrait extends Trait {

    public MenuTrait(){
        super("menutrait");
        plugin = JavaPlugin.getPlugin(Main.class);
    }

    Main plugin;

    @EventHandler
    public void click(NPCRightClickEvent e){
        if(e.getNPC() == this.getNPC()){
            e.getClicker().playSound(e.getClicker().getLocation(), Sound.NOTE_PLING,1,1);
            new NPCInv().open(e.getClicker());
        }
    }

    @Override
    public void onAttach() {
        plugin.getServer().getLogger().info(npc.getName() + " has been assigned MyTrait!");
    }

}
