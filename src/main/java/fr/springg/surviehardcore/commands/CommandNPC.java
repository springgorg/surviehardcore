package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.npc.NPCManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandNPC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("customnpc")){

            if(sender instanceof Player){
                Player p = (Player) sender;

                if(p.hasPermission("npc.perm")){

                    if(args.length == 0){
                        p.sendMessage("§c/customnpc create <nom>");
                        return false;
                    }

                    if (args[0].equalsIgnoreCase("create")) {
                        String name = args[1];
                        new NPCManager().createNPC(p, name);
                        p.sendMessage("§aVous avez crée le NPC §e" + name + "§a avec succès !");
                        p.playSound(p.getLocation(), Sound.LEVEL_UP,1,1);
                    }

                } else {
                    p.sendMessage("§cVous ne pouvez pas executer cette commande !");
                    return false;
                }

            } else {
                sender.sendMessage("§cVous ne pouvez pas executer cette commande !");
                return false;
            }

        }

        return false;
    }
}
