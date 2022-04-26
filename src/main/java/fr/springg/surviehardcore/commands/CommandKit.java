package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("kit")){

            if(sender instanceof Player){
                Player p = (Player) sender;

                if(Main.getInstance().cooldowns.containsKey(p.getName())){
                    long seconds = ((Main.getInstance().cooldowns.get(p.getName())/1000)+ Main.getInstance().cooldowntime) - (System.currentTimeMillis()/1000);

                    if(seconds>0){
                        p.sendMessage("§cVous ne pouvez plus executer cette commande pendant " + seconds + " secondes !");
                        return false;
                    }
                } else {
                    ItemBuilder h = new ItemBuilder(Material.LEATHER_HELMET).setName("§cKit");
                    ItemBuilder c = new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§cKit");
                    ItemBuilder l = new ItemBuilder(Material.LEATHER_LEGGINGS).setName("§cKit");
                    ItemBuilder b = new ItemBuilder(Material.LEATHER_BOOTS).setName("§cKit");

                    ItemBuilder bouffe = new ItemBuilder(Material.COOKED_BEEF, 64);

                    p.getInventory().setHelmet(h.toItemStack());
                    p.getInventory().setChestplate(c.toItemStack());
                    p.getInventory().setLeggings(l.toItemStack());
                    p.getInventory().setBoots(b.toItemStack());

                    p.getInventory().addItem(bouffe.toItemStack());

                    p.sendMessage("§aVous venez de recevoir le kit !");
                    Main.getInstance().cooldowns.put(p.getName(), System.currentTimeMillis());
                }

            } else {
                sender.sendMessage("§cVous ne pouvez pas executer cette commande !");
                return false;
            }

        }

        return false;
    }
}
