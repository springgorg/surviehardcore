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
                        p.sendMessage(" ");
                        p.sendMessage("§c/hologram create <name>");
                        p.sendMessage("§c/hologram list");
                        p.sendMessage("§c/hologram delete <name>");
                        p.sendMessage(" ");
                        p.sendMessage("§c[SHC]----[HOLOGRAM]----[SHC]");
                        return false;
                    }

                    if(args[0].equalsIgnoreCase("create")){
                        if(args.length > 1) {
                            String name = "";
                            for(int i = 1; i != args.length; i++) name += args[i] + " ";
                            Hologram hologram = new Hologram(name, p.getLocation());
                            hologram.create();

                            Main.getInstance().hologramManager.getHolograms().add(hologram);

                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 2);
                            p.sendMessage("§aVous avez crée l'hologramme §d" + name + "§a avec succès !");
                        }
                    } else if(args[0].equalsIgnoreCase("list")){
                        new HologramInv().open(p);
                        p.playSound(p.getLocation(), Sound.NOTE_PLING,1,2);
                    } else if(args[0].equalsIgnoreCase("delete")){
                        if(args.length > 1){
                            String name = "";
                            for(int i = 1; i!=args.length;i++) name += args[i] + " ";

                            Hologram hologram = new Hologram(name, p.getLocation());
                            if(Main.getInstance().hologramManager.getHolograms().contains(hologram)) {
                                hologram.delete();
                                Main.getInstance().hologramManager.getHolograms().remove(hologram);
                            } else {
                                p.sendMessage("§cCet hologramme n'existe pas !");
                                return false;
                            }
                        }
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
