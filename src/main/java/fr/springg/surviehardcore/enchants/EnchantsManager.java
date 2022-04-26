package fr.springg.surviehardcore.enchants;

import fr.springg.surviehardcore.enchants.enchant.FrostWalker;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantsManager {

    FrostWalker fEnch = new FrostWalker(101);
    public static List<Enchantment> enchants = new ArrayList<>();

    public void init(){
        registerEnchants(fEnch);
        enchants.add(fEnch);
    }

    private void registerEnchants(Enchantment ench){
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(ench);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disable(Enchantment ench){
        try {
            Field byIdF = Enchantment.class.getDeclaredField("byId");
            Field byNameF = Enchantment.class.getDeclaredField("byName");

            byIdF.setAccessible(true);
            byNameF.setAccessible(true);

            Map<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdF.get(null);
            Map<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameF.get(null);

            if(byId.containsKey(ench.getId())){
                byId.remove(ench.getId());
            }

            if(byName.containsKey(ench.getName())){
                byName.remove(ench.getName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
