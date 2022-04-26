package fr.springg.surviehardcore.npc;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.invs.BackroomsInv;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

@TraitName("NPCTrait")
public class NPCTrait extends Trait {
    public NPCTrait(){
        super("npctrait");
        plugin = JavaPlugin.getPlugin(Main.class);
    }

    Main plugin;

    @EventHandler
    public void click(NPCRightClickEvent e){
        if(e.getNPC() == this.getNPC()){
            e.getClicker().playSound(e.getClicker().getLocation(), Sound.BLAZE_DEATH,2,0);
            new BackroomsInv().open(e.getClicker());
        }
    }

    @Override
    public void onAttach() {
        plugin.getServer().getLogger().info(npc.getName() + " has been assigned MyTrait!");
    }

}
