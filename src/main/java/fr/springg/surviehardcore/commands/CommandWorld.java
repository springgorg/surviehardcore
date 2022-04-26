package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.invs.WorldInv;
import fr.springg.surviehardcore.utils.Schematic;
import fr.springg.surviehardcore.utils.WorldUtils;
import fr.springg.surviehardcore.worlds.CustomChunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("worlds")){

            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.hasPermission("worlds.perm")){

                    if(args.length == 0){
                        p.playSound(p.getLocation(), Sound.VILLAGER_NO,1,1);
                        p.sendMessage("§c/worlds list");
                        p.sendMessage("§c/worlds create <nom>");
                        p.sendMessage("§c/worlds tp <world>");
                        return false;
                    }

                    if(args[0].equalsIgnoreCase("list")){
                        p.playSound(p.getLocation(), Sound.NOTE_PLING,1,1);
                        new WorldInv().open(p);
                    } else if (args[0].equalsIgnoreCase("create")){
                        if(args[1].length() == 0){
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO,1,1);
                            p.sendMessage("§cVeuillez mettre un nom !");
                            return false;
                        } else {
                            String worldName = args[1];
                            WorldUtils.create(worldName);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                            p.sendMessage("§aVous avez crée le monde §d" + worldName + "§a avec succès !");
                        }
                    } else if(args[0].equalsIgnoreCase("tp")){
                        if(args[1].length() == 0){
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO,1,1);
                            p.sendMessage("§cVeuillez mettre un nom !");
                            return false;
                        } else {
                            Location loc = new Location(Bukkit.getWorld(args[1]), Bukkit.getWorld(args[1]).getSpawnLocation().getX(),Bukkit.getWorld(args[1]).getSpawnLocation().getY(),Bukkit.getWorld(args[1]).getSpawnLocation().getZ());
                            p.teleport(loc);
                            p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL,1,1);
                        }
                    }

                } else {
                    p.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                    return false;
                }

            } else {
                sender.sendMessage("§cVous ne pouvez executer cette commande !");
                return false;
            }

        }

        return false;
    }
}
