package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.holograms.Hologram;
import fr.springg.surviehardcore.invs.HologramInv;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHologram implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("hologram")){

            if(sender instanceof Player){
                Player p = (Player) sender;

                if(p.hasPermission("hologram.perm")) {

                    if(args.length == 0){
                        p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1,1);
                        p.sendMessage("§c[SHC]----[HOLOGRAM]----[SHC]");
                        p.sendMessage("§c/hologram create <name>");
                        p.sendMessage("§c/hologram list");
                        p.sendMessage("§c/hologram delete <name>");
                        p.sendMessage("§c[SHC]----[HOLOGRAM]----[SHC]");
                        return false;
                    }

                    if(args[0].equalsIgnoreCase("create")){
                        String name = args[1];
                        Hologram hologram = new Hologram(name, p.getLocation());
                        hologram.create();

                        Main.getInstance().hologramManager.getHolograms().add(hologram);

                        p.playSound(p.getLocation(), Sound.LEVEL_UP,1,2);
                        p.sendMessage("§aVous avez crée l'hologramme §d" + name + "§a avec succès !");
                    } else if(args[0].equalsIgnoreCase("list")){
                        new HologramInv().open(p);
                        p.playSound(p.getLocation(), Sound.NOTE_PLING,1,2);
                    } else if(args[0].equalsIgnoreCase("delete")){
                        // EN COURS
                    }

                } else {
                    p.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                    return false;
                }

            } else {
                sender.sendMessage("§cVous ne pouvez pas utiliser cette commande !");
                return false;
            }

        }

        return false;
    }
}
